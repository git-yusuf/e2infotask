package com.yusuf.e2infotask.repos;

import com.yusuf.e2infotask.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);

    @Query(value = "from Employee e where e.lastUpdated > :lastUpdated")
    List<Employee> findEmployeesForBackup(LocalDateTime lastUpdated);


}
