package com.example1.controllers;

import com.example1.etities.Book;
import com.example1.etities.Shelve;
import com.example1.repositories.ShelveRepository;
import com.example1.services.ShelveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/shelves")
public class ShelveController {
  @Autowired ShelveService shelveService;

  @PostMapping("/create-shelves")
  public ResponseEntity <Shelve> addShelve(@RequestBody Shelve shelve){
    return new ResponseEntity(shelveService.saveShelve(shelve), HttpStatus.CREATED );
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Shelve> addBook(@PathVariable Integer id, @RequestBody List<Book> books){
    return new ResponseEntity(shelveService.saveBook(id, books), HttpStatus.OK);
  }


}
