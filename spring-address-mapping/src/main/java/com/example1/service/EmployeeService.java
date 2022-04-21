package com.example1.service;

import com.example1.Dto.Request;
import com.example1.entity.Address;
import com.example1.entity.Employee;
import com.example1.repository.AddressRepository;
import com.example1.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private AddressRepository addressRepository;

  public List<Employee> findAllEmployee() {
    return employeeRepository.findAll();
  }

  @Transactional
  public Employee saveEmployee(Request request) {
    List<Address> addressList = new ArrayList<>();
    request.getEmployee().getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());
    Employee employee = request.getEmployee();
    employee.setAddressList(addressList);
    return employeeRepository.save(employee);
  }

  public Employee findById(Integer id) {
    return employeeRepository.findByid(id);

  }

  public Employee updateEmployee(Integer id, Employee employee) {
    Employee existingEmployee = employeeRepository.findById(id).orElse(null);
    List<Address> addressList = new ArrayList<>();
    employee.getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());
    existingEmployee.setAddressList(addressList);
    existingEmployee.setName(employee.getName());
    existingEmployee.setEmail(employee.getEmail());
    existingEmployee.setPhone(employee.getPhone());

    return employeeRepository.save(existingEmployee);

  }

  public Employee deleteEmployee(Integer id) {
    Employee employee = findById(id);
    employeeRepository.deleteById(employee.getId());
    return employee;

  }

  public List<Employee> sortEmployee(String field) {
    return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
  }

  public List<Employee> filterEmployee(String name) {
    List<Employee> filteredEmployee = new ArrayList<>();
    List<Employee> employees = employeeRepository.findAll();
    for (Employee employee : employees) {

      if (employee.getName().equalsIgnoreCase(name) || employee.getEmail().equalsIgnoreCase(name)) {
        filteredEmployee.add(employee);

      }
      for (Address address : employee.getAddressList()) {
        if (address.getCity().equalsIgnoreCase(name) || address.getCountry().equalsIgnoreCase(name)
            || address.getState().equalsIgnoreCase(name)) {
          filteredEmployee.add(employee);
        }
      }

    }

    return filteredEmployee;
  }

  public List<Employee> findByDepartment(String department) {
    return employeeRepository.findBydepartment(department);
  }

}
