package com.paypal.bfs.test.employeeserv.ft;

import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.ft.mock.EmployeeMock;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EmployeeservApplication.class)
@ActiveProfiles("functional test")
public class EmployeeservApplicationTest {
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    /**
     *  Functional Test - To test the behavior of Employee rest api of listing employees , specific employee by id and
     *  create api of employee with happy and error scenarios.
     *
     **/

    @Test
    public void testEmployeeApi() throws Exception {

        // test employeeGetById when id Not Found
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1/bfs/employees/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "Employee with Id 1 not found.";

        Assert.assertEquals(expected, (new JSONObject(response.getBody())).getString("message"));


        // test create api with constraint violations with date value is not in right format
        HttpEntity<Employee> entity1 = new HttpEntity<Employee>(EmployeeMock.getEmployeeErrorObject(), headers);

        response = restTemplate.exchange(
                createURLWithPort("/v1/bfs/employees"),
                HttpMethod.POST, entity1, String.class);

       expected = "[\"Date Of Birth value: '12\\/31\\/1989' must be in dd\\/mm\\/yyyy format.\"]";

        Assert.assertEquals(expected, (new JSONObject(response.getBody())).getString("message"));

        // test create api in happy scenarios

        entity1 = new HttpEntity<Employee>(EmployeeMock.getEmployeeObject(), headers);

        response = restTemplate.exchange(
                createURLWithPort("/v1/bfs/employees"),
                HttpMethod.POST, entity1, String.class);

        expected = "Success!! Employee created with id: 1";

        Assert.assertEquals(expected, response.getBody());

        // test employee by id found scenario
        entity = new HttpEntity<>(null, headers);

        response = restTemplate.exchange(
                createURLWithPort("/v1/bfs/employees/1"),
                HttpMethod.GET, entity, String.class);

        expected = "{\"id\":1,\"first_name\":\"Virat\",\"last_name\":\"Kohli\",\"date_of_birth\":\"31/12/1989\",\"address\":{\"line1\":\"3rd Main\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"country\":\"India\",\"zip_code\":\"560087\"}}";

        Assert.assertEquals(expected, response.getBody());

        // test Get all employees
        entity = new HttpEntity<String>(null, headers);

        response = restTemplate.exchange(
                createURLWithPort("/v1/bfs/employees"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(EmployeeMock.getEmployeeResponse(), response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}