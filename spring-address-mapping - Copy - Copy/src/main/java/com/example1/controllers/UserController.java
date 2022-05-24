package com.example1.controllers;

import com.example1.etities.Book;
import com.example1.etities.User;
import com.example1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController @RequestMapping("/users") public class UserController {
  @Autowired private UserService userService;

  @PostMapping("/")
  public User saveUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PatchMapping("/issueBook")
  public String issuBook(@RequestParam Integer userId, @RequestBody List <Book> books) {
    return userService.issueBook(userId, books);
  }

  @PatchMapping("/deactivateUser")
  public ResponseEntity <String> deActivateUser(@RequestParam Integer userId) {
    return new ResponseEntity <>(userService.deActivate(userId), HttpStatus.OK);
  }

  @PatchMapping("/activateUser")
  public ResponseEntity <String> activateUser(@RequestParam Integer userId) {
    return new ResponseEntity(userService.activateUser(userId), HttpStatus.OK);
  }

  @PatchMapping("/returnBook")
  public String returnedBook(@RequestParam Integer userId,
      @RequestBody List <Book> bookList) {
    return userService.returnBook(userId, bookList);
  }

}
