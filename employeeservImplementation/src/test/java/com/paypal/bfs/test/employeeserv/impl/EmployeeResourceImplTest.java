package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.mock.EmployeeMock;
import com.paypal.bfs.test.employeeserv.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeResourceImpl.class)
public class EmployeeResourceImplTest {
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEmployeeGetById() throws Exception {

        Mockito.when(employeeService.getEmployeeById("1")).thenReturn(EmployeeMock.getEmployeeObject());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Mockito.reset(employeeService);
        Assert.assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void testEmployeeGetByIdDoesNotExist() throws Exception {

        Mockito.when(employeeService.getEmployeeById("1")).thenThrow(new EmployeeNotFoundException("1"));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
        Mockito.reset(employeeService);
        Assert.assertEquals("Employee with Id 1 not found.", (new JSONObject(result.getResponse().getContentAsString())).getString("message"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {

        Mockito.when(employeeService.getEmployeesList()).thenReturn(EmployeeMock.getEmployeeList());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Mockito.reset(employeeService);
        Assert.assertEquals(2, (new JSONArray(result.getResponse().getContentAsString())).length());
    }

    @Test
    public void testCreateEmployee() throws Exception {

        Mockito.when(employeeService.save(EmployeeMock.getEmployeeObject())).thenReturn(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(EmployeeMock.getEmployeeRequestObject()))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        Mockito.reset(employeeService);
        Assert.assertEquals("Success!! Employee created with id: 1", result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateEmployeeWithInvalidPropertyError() throws Exception {
        Employee employee = EmployeeMock.getEmployeeObject();
        Mockito.when(employeeService.save(employee)).thenReturn(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(EmployeeMock.getEmployeeRequestWithAdditionalObject()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
        Mockito.reset(employeeService);
        Assert.assertEquals("Invalid property: {test=31/12/1989} found.", (new JSONObject(result.getResponse().getContentAsString())).getString("message"));
    }

    @Test
    public void testEmployeeGetByIdWithConstraintError() throws Exception {
        Set<? extends ConstraintViolation<Employee>> constraintViolations = new HashSet<>();
        ConstraintViolationException ex = new ConstraintViolationException("", constraintViolations);
        Mockito.when(employeeService.getEmployeeById("1")).thenThrow(new TransactionSystemException("", ex));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
        Mockito.reset(employeeService);
        Assert.assertNotNull((new JSONObject(result.getResponse().getContentAsString())).getString("message"));
    }
}
