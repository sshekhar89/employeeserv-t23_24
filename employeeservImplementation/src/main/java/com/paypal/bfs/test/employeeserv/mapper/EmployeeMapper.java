package com.paypal.bfs.test.employeeserv.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.exception.InvalidAdditionalPropertyException;
import com.paypal.bfs.test.employeeserv.model.Employee;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static com.paypal.bfs.test.employeeserv.model.Employee mapToBL(com.paypal.bfs.test.employeeserv.api.model.Employee employee) {
        com.paypal.bfs.test.employeeserv.model.Employee result = null;
        if (employee != null) {
            if (!CollectionUtils.isEmpty(employee.getAdditionalProperties())) {
                throw  new InvalidAdditionalPropertyException(employee.getAdditionalProperties());
            }
            result = new com.paypal.bfs.test.employeeserv.model.Employee();
            result.setId(employee.getId());
            result.setDateOfBirth(employee.getDateOfBirth());
            result.setFirstName(employee.getFirstName());
            result.setLastName(employee.getLastName());
            ObjectMapper mapper = new ObjectMapper();
            com.paypal.bfs.test.employeeserv.api.model.Address address = mapper.convertValue(employee.getAddress(),com.paypal.bfs.test.employeeserv.api.model.Address.class);
            result.setAddress(mapToBL(address));
        }
        return result;
    }

    private static com.paypal.bfs.test.employeeserv.model.Address mapToBL(com.paypal.bfs.test.employeeserv.api.model.Address address) {
        com.paypal.bfs.test.employeeserv.model.Address result = null;
        if (address !=null) {
            if (!CollectionUtils.isEmpty(address.getAdditionalProperties())) {
                throw  new InvalidAdditionalPropertyException(address.getAdditionalProperties());
            }
            result = new com.paypal.bfs.test.employeeserv.model.Address();
            result.setLine1(address.getLine1());
            result.setLine2(address.getLine2());
            result.setCity(address.getCity());
            result.setState(address.getState());
            result.setZipCode(address.getZipCode());
            result.setCountry(address.getCountry());
        }
        return result;
    }

    public static com.paypal.bfs.test.employeeserv.api.model.Employee mapToRequest(Employee employee) {
        com.paypal.bfs.test.employeeserv.api.model.Employee result = null;
        if (employee != null) {
            result = new com.paypal.bfs.test.employeeserv.api.model.Employee();
            result.setId(employee.getId());
            result.setDateOfBirth(employee.getDateOfBirth());
            result.setFirstName(employee.getFirstName());
            result.setLastName(employee.getLastName());
            result.setAddress(mapToRequest(employee.getAddress()));
        }
        return result;
    }

    public static List<com.paypal.bfs.test.employeeserv.api.model.Employee> mapToRequest(Iterable<com.paypal.bfs.test.employeeserv.model.Employee> employee) {
        List<com.paypal.bfs.test.employeeserv.api.model.Employee> result = new ArrayList<com.paypal.bfs.test.employeeserv.api.model.Employee>();
        for (com.paypal.bfs.test.employeeserv.model.Employee e : employee) {
            result.add(mapToRequest(e));
        }
        return result;
    }
    private static com.paypal.bfs.test.employeeserv.api.model.Address mapToRequest(com.paypal.bfs.test.employeeserv.model.Address address) {
        com.paypal.bfs.test.employeeserv.api.model.Address result = null;
        if (address != null) {
            result = new com.paypal.bfs.test.employeeserv.api.model.Address();
            result.setLine1(address.getLine1());
            result.setLine2(address.getLine2());
            result.setCity(address.getCity());
            result.setState(address.getState());
            result.setZipCode(address.getZipCode());
            result.setCountry(address.getCountry());
        }
        return result;
    }
}
