package com.example1.etities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "User_Table")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String status;
  @Temporal(TemporalType.TIMESTAMP )
  private Date createdOn= new Date();
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date modifiedOn = new Date();
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date deActivatedOn;
  @OneToMany
  private List <Book> books;
}
