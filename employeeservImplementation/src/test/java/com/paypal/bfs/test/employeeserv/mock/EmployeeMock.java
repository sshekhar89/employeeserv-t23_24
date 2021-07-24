package com.paypal.bfs.test.employeeserv.mock;

import com.paypal.bfs.test.employeeserv.model.Address;
import com.paypal.bfs.test.employeeserv.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMock {

    public static String getEmployeeRequestObject() {
        return "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"first_name\": \"Virat\",\n" +
                "    \"last_name\": \"Kohli\",\n" +
                "    \"date_of_birth\": \"31/12/1989\",\n" +
                "    \n" +
                "    \"address\": {\n" +
                "        \"line1\": \"3rd Main\",\n" +
                "        \"city\": \"Bangalore\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"state\": \"Karnataka\",\n" +
                "        \"zip_code\": \"560087\"\n" +
                "    }\n" +
                "\n" +
                "}";
    }

    public static String getEmployeeRequestWithAdditionalObject() {
        return "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"first_name\": \"Virat\",\n" +
                "    \"last_name\": \"Kohli\",\n" +
                "    \"date_of_birth\": \"31/12/1989\",\n" +
                "    \"test\": \"31/12/1989\",\n" +
                "    \n" +
                "    \"address\": {\n" +
                "        \"line1\": \"3rd Main\",\n" +
                "        \"city\": \"Bangalore\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"state\": \"Karnataka\",\n" +
                "        \"zip_code\": \"560087\"\n" +
                "    }\n" +
                "\n" +
                "}";
    }

    public static Employee getEmployeeObject() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setDateOfBirth("31/12/1989");
        employee.setFirstName("Virat");
        employee.setLastName("Kohli");
        employee.setAddress(getAddressObject());
        return employee;
    }

    public static List<Employee> getEmployeeList() {
        List<Employee> list = new ArrayList<>();
        list.add(getEmployeeObject());
        list.add(getEmployeeObject());
        return list;
    }

    public static Address getAddressObject() {
        Address address = new Address();
        address.setLine1("3rd Main");
        address.setCity("Bangalore");
        address.setCountry("India");
        address.setState("Karnataka");
        address.setZipCode("560087");
        return address;
    }
}
