package com.yusuf.e2infotask.controllers;

import com.yusuf.e2infotask.models.Department;
import com.yusuf.e2infotask.models.Employee;
import com.yusuf.e2infotask.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepo deptRepo;

    @GetMapping(path = "/all")
    public List<Department> getAllDepartments(){
        return deptRepo.findAll();
    }

    @GetMapping(path = "/id/{dept_id}")
    public Optional<Department> getDepartmentById(@PathVariable Long dept_id){
        return deptRepo.findById(dept_id);
    }

    @GetMapping(path = "/name/{dept_name}")
    public Optional<Department> getDepartmentByName(@PathVariable String dept_name){
        return deptRepo.findByDeptName(dept_name);
    }

    @PostMapping(path = "/addDepartment")
    public ResponseEntity addDepartment(@RequestBody Department dept) {

        Department newDept =  deptRepo.save(dept);
        if (newDept.getId() != null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
