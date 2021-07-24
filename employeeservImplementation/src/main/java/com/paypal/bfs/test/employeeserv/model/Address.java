package com.paypal.bfs.test.employeeserv.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen",strategy="foreign",parameters=@Parameter(name="property",value="employee"))
    @Column(name="id",nullable=false,unique=true)
    private Integer id;

    @NotBlank(message = "Line1 - must not be empty.")
    @Size(min=2, max = 255, message = "Line1 value: '${validatedValue}' must be between {min} and {max} characters long.")
    private String line1;

    private String line2;

    @NotBlank(message = "City - must not be empty.")
    @Size(min=3, max = 85, message = "City value: '${validatedValue}' must be between {min} and {max} characters long.")
    private String city;

    @NotBlank(message = "State - must not be empty.")
    @Size(min=2, max = 85 , message = "State value: '${validatedValue}' must be between {min} and {max} characters long.")
    private String state;

    @NotBlank(message = "Country - must not be empty.")
    @Pattern(regexp = "[a-zA-Z]{2,56}", message = "Country value: '${validatedValue}' must be between 2 and 56 characters long.")
    private String country;

    @NotBlank(message = "Zip Code - must not be empty.")
    @Pattern(regexp = "(^\\d{5}$)|(^\\d{6}$)", message = "Zip Code value: '${validatedValue}' must be between 5 / 6 characters long.")
    private String zipCode;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Employee employee;

    @Override
    public String toString() {
        return "Address [id=" + id + ", line1=" + line1 + ", line2=" + line2 + ", city=" + city
                + ", state=" + state +  ", zipCode=" + zipCode + ", country=" + country + "]";
    }
}
