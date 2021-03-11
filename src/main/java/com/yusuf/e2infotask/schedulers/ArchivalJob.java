package com.yusuf.e2infotask.schedulers;

import com.yusuf.e2infotask.models.Employee;
import com.yusuf.e2infotask.models.EmployeeBackup;
import com.yusuf.e2infotask.repos.EmployeeBackupRepo;
import com.yusuf.e2infotask.repos.EmployeeRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ArchivalJob {

    @Autowired
    EmployeeRepo empRepo;

    @Autowired
    EmployeeBackupRepo empBackupRepo;

    //@Scheduled(cron = "${cron.archivaljob}")
    @Scheduled(fixedRate = 20000)
    public void archiveEmployee(){
        log.info("Entering archival job");

        List<Employee> employeesToBackup;
        List<EmployeeBackup> empBackupList;

        if (empBackupRepo.count() == 0){
            log.info("First time backup. Should save all records from Employee");
            employeesToBackup = empRepo.findAll();
            empBackupList = buildBackupRecords(employeesToBackup);
        } else {
            log.info("Should save recent records since last backup from Employee");
            LocalDateTime lastBackupOn = empBackupRepo.getLastUpdatedDate();

            employeesToBackup = empRepo.findEmployeesForBackup(lastBackupOn);
            log.info("Backup size : " + employeesToBackup.size());

            empBackupList = buildBackupRecords(employeesToBackup);

        }
        empBackupRepo.saveAll(empBackupList);

    }

    private List<EmployeeBackup> buildBackupRecords(List<Employee> employeesToBackup) {
        List<EmployeeBackup> empBackupList = employeesToBackup.parallelStream().map(emp -> {
            EmployeeBackup backup = new EmployeeBackup();
            backup.setId(emp.getId());
            backup.setName(emp.getName());
            backup.setSalary(emp.getSalary());
            backup.setDepartment(emp.getDepartment());
            backup.setLastUpdated(emp.getLastUpdated());
            return backup;
        }).collect(Collectors.toList());
        return empBackupList;
    }
}
