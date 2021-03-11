package com.yusuf.e2infotask.repos;

import com.yusuf.e2infotask.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findByDeptName(String deptName);
}
