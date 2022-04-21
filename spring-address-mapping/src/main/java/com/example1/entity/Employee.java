package com.example1.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Employee_Table")
// @Scope("prototype")
public class Employee {
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Address> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<Address> addressList) {
    this.addressList = addressList;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotEmpty(message = "name can not be blank")
  @Size(max = 10, min = 3, message = "Please Enter the name with proper lenghth")
  private String name;
  @NotEmpty(message = "email can not be blank")
  private String email;
  @NotEmpty(message = "Phone can not be blank")
  @Size(max = 10, message = "Please Enter Valid length phone No")
  private String phone;

  @NotEmpty(message = "Departmet can not be blank")
  @Size(max = 5, min = 2, message = "Please Enter Valid lenght department")
  private String department;

  @JoinColumn(name = "employee_id")
  @OneToMany(cascade = CascadeType.ALL)
  private List<Address> addressList;

}
