package com.paypal.bfs.test.employeeserv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "First Name - must not be empty.")
    @Size(min=2, max = 50, message = "First Name value: '${validatedValue}' must be between {min} and {max} characters long.")
    private String firstName;

    @NotBlank(message = "Last Name - must not be empty.")
    @Size(min=2, max = 50, message = "Last Name value: '${validatedValue}' must be between {min} and {max} characters long.")
    private String lastName;

    @NotBlank(message = "Date Of Birth - must not be empty.")
    @Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)",
            message = "Date Of Birth value: '${validatedValue}' must be in dd/mm/yyyy format.")
    private  String dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Address address;

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", dateOfBirth=" + dateOfBirth + ", address=" + address
                + "]";
    }
}
