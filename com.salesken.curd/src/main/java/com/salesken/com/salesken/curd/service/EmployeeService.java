package com.salesken.com.salesken.curd.service;

import com.salesken.com.salesken.curd.SaleskenException;
import com.salesken.com.salesken.curd.dao.EmployeeRepository;
import com.salesken.com.salesken.curd.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class EmployeeService {
  @Autowired
  EmployeeRepository repository;

  public List<Employee> getAllEmployees()
  {
    List<Employee> employeeList = repository.findAll();
      return employeeList;
  }
  @Lock(LockModeType.PESSIMISTIC_READ)
  public Employee getEmployeeById(Long id) throws SaleskenException
  {
    Optional<Employee> employee = repository.findById(id);

    if(employee.isPresent()) {
      return employee.get();
    } else {
      throw new SaleskenException("No employee record exist for given id");
    }
  }
@Lock(LockModeType.PESSIMISTIC_WRITE)
  public Employee createOrUpdateEmployee(Employee entity) throws SaleskenException
  {
    Optional<Employee> employee = repository.findById(entity.getId());

    if(employee.isPresent())
    {
      Employee newEntity = employee.get();
      newEntity.setEmail(entity.getEmail());
      newEntity.setFirstName(entity.getFirstName());
      newEntity.setLastName(entity.getLastName());
      newEntity = repository.save(newEntity);
      return newEntity;
    } else {
      entity = repository.save(entity);
      return entity;
    }
  }

  public void deleteEmployeeById(Long id) throws SaleskenException
  {
    Optional<Employee> employee = repository.findById(id);
    if(employee.isPresent())
    {
      repository.deleteById(id);
    } else {
      throw new SaleskenException("no record found");
    }
  }
}
