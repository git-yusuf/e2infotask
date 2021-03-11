package com.yusuf.e2infotask.controllers;

import com.yusuf.e2infotask.models.Department;
import com.yusuf.e2infotask.models.Employee;
import com.yusuf.e2infotask.repos.DepartmentRepo;
import com.yusuf.e2infotask.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @Autowired
    DepartmentRepo deptRepo;

    @GetMapping(path = "/all")
    public List<Employee> getAllEmployees(){
        return empRepo.findAll();
    }

    @GetMapping(path = "/id/{emp_id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long emp_id){
       return empRepo.findById(emp_id);
    }

    @GetMapping(path = "/name/{emp_name}")
    public Optional<Employee> getEmployeeByName(@PathVariable String emp_name){
        return empRepo.findByName(emp_name);
    }

    @PostMapping(path = "/addEmployee")
    public ResponseEntity addEmployee(@RequestBody Employee emp) {
        emp.setLastUpdated(LocalDateTime.now());
        Employee newEmp =  empRepo.save(emp);
        if (newEmp.getId() != null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "{emp_id}/department/{dept_id}")
    public ResponseEntity<Object> assignDepartment(@PathVariable Long emp_id, @PathVariable Long dept_id){
        Optional<Employee> emp = empRepo.findById(emp_id);
        Optional<Department> dept = deptRepo.findById(dept_id);

        if (emp.isPresent() && dept.isPresent()){
            Employee tempEmp = emp.get();
            Department tempDept = dept.get();

            tempEmp.setDepartment(tempDept);
            tempEmp.setLastUpdated(LocalDateTime.now());

            return ResponseEntity.ok(empRepo.save(tempEmp));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping(path = "/deleteEmployee/{emp_id}")
    public void deleteEmployee(@PathVariable Long emp_id){
        empRepo.deleteById(emp_id);
    }

}
