package com.example1.controller;

import com.example1.Dto.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example1.entity.Address;
import com.example1.entity.Employee;
import com.example1.service.EmployeeService;

//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
import com.example1.repository.AddressRepository;
import com.example1.repository.EmployeeRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private ExceptionController exceptionController;

  @PostMapping("/employees")
  public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Request request) {
    return new ResponseEntity<Employee>(employeeService.saveEmployee(request), HttpStatus.CREATED);
  }

  @GetMapping("/employees")
  public List<Employee> findAllEmployee() {
    return employeeService.findAllEmployee();
  }

  @GetMapping("/employees/{id}")
  public Employee findById(@PathVariable int id) {
    return employeeService.findById(id);
  }

  @PutMapping("/employees/{id}")
  public Employee updateEmployee(@RequestBody Request request, @PathVariable Integer id) {
    Employee employee = request.getEmployee();
    return employeeService.updateEmployee(id, request.getEmployee());

  }

  @DeleteMapping("/employees")
  public Employee deleteEmployee(@PathVariable int id) {
    return employeeService.deleteEmployee(id);
  }

  @GetMapping("/employees/sorts")
  public List<Employee> sortEmployee(@RequestParam String field) {
    List<Employee> sortedEmployee = employeeService.sortEmployee(field);
    return sortedEmployee;
  }

  @GetMapping("/employees/filters")
  public List<Employee> filetrEmployee(@RequestParam String name) {
    List<Employee> filetredEmployee = employeeService.filterEmployee(name);
    return filetredEmployee;
  }

  @GetMapping("/employees/filterByName")
  public List<Employee> filterByName(@RequestParam String name) {
    return employeeRepository.findByname(name);
  }

  @GetMapping("/employees/filterByEmail")
  public List<Employee> findByemail(@RequestParam String email) {
    return employeeRepository.findByemail(email);
  }

  @GetMapping("/employees/findByDepartment")
  public List<Employee> findByDepartmet(@RequestParam String department) {
    return employeeService.findByDepartment(department);
  }

}
