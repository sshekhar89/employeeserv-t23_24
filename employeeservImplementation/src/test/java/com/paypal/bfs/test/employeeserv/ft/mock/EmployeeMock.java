package com.paypal.bfs.test.employeeserv.ft.mock;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.api.model.Address;
public class EmployeeMock {

    public static String getEmployeeResponse() {
        return "[{\"id\":1,\"first_name\":\"Virat\",\"last_name\":\"Kohli\",\"date_of_birth\":\"31/12/1989\",\"address\":{\"line1\":\"3rd Main\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"country\":\"India\",\"zip_code\":\"560087\"}}]";
    }

    public static Employee getEmployeeErrorObject() {
        Employee employee = new Employee();
        employee.setDateOfBirth("12/31/1989");
        employee.setFirstName("Virat");
        employee.setLastName("Kohli");
        employee.setAddress(getAddressObject());
        return employee;
    }
    public static Employee getEmployeeObject() {
        Employee employee = new Employee();
        employee.setDateOfBirth("31/12/1989");
        employee.setFirstName("Virat");
        employee.setLastName("Kohli");
        employee.setAddress(getAddressObject());
        return employee;
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