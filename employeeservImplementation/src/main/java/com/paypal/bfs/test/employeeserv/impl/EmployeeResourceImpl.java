package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.mapper.EmployeeMapper;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(EmployeeMapper.mapToRequest(employeeService.getEmployeesList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        return new ResponseEntity<>(EmployeeMapper.mapToRequest(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createEmployee(Employee employee) {
        com.paypal.bfs.test.employeeserv.model.Employee employeeBl = EmployeeMapper.mapToBL(employee);
        employeeService.save(employeeBl);
        return new ResponseEntity(String.format("Success!! Employee created with id: %d", employeeBl.getId()), HttpStatus.CREATED);
    }
}
