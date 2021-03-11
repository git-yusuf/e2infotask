package com.yusuf.e2infotask.models;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EMPLOYEEBACKUP")
public class EmployeeBackup {

  
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
