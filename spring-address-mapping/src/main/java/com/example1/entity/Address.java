package com.example1.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
// @Scope("prototype")
@Table(name = "Address_Table")
public class Address {
  @Id
  @GeneratedValue
  private Integer id;
  @NotEmpty(message = "Street1 can not be blank")
  private String street1;

  private String street2;
  @NotEmpty(message = "City can not be blank")
  private String city;
  @NotEmpty(message = "State can not be blank")
  private String state;
  @NotEmpty(message = "Country can not be blank")
  private String country;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  // @ManyToOne
  // private Employee employee;

}
