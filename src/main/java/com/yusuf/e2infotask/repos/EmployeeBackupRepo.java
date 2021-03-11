package com.yusuf.e2infotask.repos;

import com.yusuf.e2infotask.models.EmployeeBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EmployeeBackupRepo extends JpaRepository<EmployeeBackup, Long> {
    @Query(value = "select MAX(lastUpdated) from EmployeeBackup")
    LocalDateTime getLastUpdatedDate();


}
