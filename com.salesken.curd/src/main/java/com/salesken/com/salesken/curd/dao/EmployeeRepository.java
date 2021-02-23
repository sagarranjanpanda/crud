package com.salesken.com.salesken.curd.dao;

import com.salesken.com.salesken.curd.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

}
