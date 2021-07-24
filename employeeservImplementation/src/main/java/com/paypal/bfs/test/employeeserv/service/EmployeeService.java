package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.model.Address;
import com.paypal.bfs.test.employeeserv.model.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Iterable<Employee> getEmployeesList() {
        return employeeRepository.findAll();
    }

    public boolean save(Employee employee) {
        Address address = employee.getAddress();
        address.setEmployee(employee);
        employee.setAddress(address);
        employeeRepository.save(employee);
        return true;
    }
}
