package com.example1.etities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @ToString @Data @Table(name = "Books_Table") public class Book {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  private String name;
  @Temporal(TemporalType.TIMESTAMP) private Date createdOn;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private Date modifiedOn = new Date();
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private Date issuedOn;

  public Date getReturnedOn() {
    return returnedOn;
  }

  public void setReturnedOn(Date returnedOn) {
    this.returnedOn = returnedOn;
  }

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private Date returnedOn;
  @OneToMany(cascade = CascadeType.ALL) private List <Shelve> shelves;

  public Date getIssuedOn() {
    return issuedOn;
  }

  public void setIssuedOn(Date issuedOn) {
    this.issuedOn = issuedOn;
  }

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

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(Date modifiedOn) {
    this.modifiedOn = modifiedOn;
  }

  public List <Shelve> getShelves() {
    return shelves;
  }

  public void setShelves(List <Shelve> shelves) {
    this.shelves = shelves;
  }

}
