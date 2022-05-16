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
@Table(name = "Shelves_Table")
public class Shelve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @Temporal(TemporalType.TIMESTAMP )
  private Date createdOn= new Date();
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date modifiedOn = new Date();
  @OneToMany(cascade = CascadeType.ALL)
  private List <Book> books;
}
