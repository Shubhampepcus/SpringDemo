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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "Books_Table")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @Temporal(TemporalType.TIMESTAMP )
  private Date createdOn;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date modifiedOn = new Date();

  @OneToMany(cascade = CascadeType.ALL)
  private List <Shelve> shelves;
}
