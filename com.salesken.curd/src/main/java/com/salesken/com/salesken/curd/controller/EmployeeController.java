package com.salesken.com.salesken.curd.controller;

import com.salesken.com.salesken.curd.SaleskenException;
import com.salesken.com.salesken.curd.model.Employee;
import com.salesken.com.salesken.curd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
  @Autowired
  EmployeeService service;

  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> list = service.getAllEmployees();

    return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id)
          throws SaleskenException {
    Employee entity = service.getEmployeeById(id);

    return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Employee> createEmployee(Employee employee)
          throws SaleskenException {
    Employee updated = service.createOrUpdateEmployee(employee);
    return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
  }
  @PutMapping("/update")
  public ResponseEntity<Employee> updateEmployee(Employee employee)
          throws SaleskenException {
    Employee updated = service.createOrUpdateEmployee(employee);
    return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
          throws SaleskenException {
    service.deleteEmployeeById(id);
    return HttpStatus.FORBIDDEN;
  }
}
