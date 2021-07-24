package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.mock.EmployeeMock;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeById() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(EmployeeMock.getEmployeeObject()));
        Assert.assertEquals(EmployeeMock.getEmployeeObject().toString(), employeeService.getEmployeeById("1").toString());
        Mockito.reset(repository);
    }

    @Test
    public void testGetEmployeesList() {
        Mockito.when(repository.findAll()).thenReturn(EmployeeMock.getEmployeeList());
        Assert.assertEquals(EmployeeMock.getEmployeeList().toString(), employeeService.getEmployeesList().toString());
        Mockito.reset(repository);
    }

    @Test
    public void testSave() {
        Mockito.when(repository.save(EmployeeMock.getEmployeeObject())).thenReturn(EmployeeMock.getEmployeeObject());
        Assert.assertTrue(employeeService.save(EmployeeMock.getEmployeeObject()));
        Mockito.reset(repository);
    }
}
