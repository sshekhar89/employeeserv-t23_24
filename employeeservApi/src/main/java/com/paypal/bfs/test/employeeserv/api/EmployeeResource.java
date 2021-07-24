package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves all the {@link Employee} resources.
     *
     * @return {@link List<Employee>} resource.
     */
    @RequestMapping(value = "/v1/bfs/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Employee>> getAllEmployees();

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value = "/v1/bfs/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    /**
     * Create the {@link Employee} resource.
     * @param employee as request body.
     * @return {@link String} resource.
     */
    @PostMapping(value = "/v1/bfs/employees", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee);
}
