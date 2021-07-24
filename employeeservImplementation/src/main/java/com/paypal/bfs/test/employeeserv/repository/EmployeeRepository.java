package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
