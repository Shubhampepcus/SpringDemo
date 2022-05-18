package com.example1.services;

import com.example1.etities.Book;
import com.example1.etities.User;
import com.example1.exception.ResourceNotFoundException;
import com.example1.repositories.BookRepository;
import com.example1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service public class UserService {
  @Autowired private BookRepository bookRepository;
  @Autowired private UserRepository userRepository;

  public User createUser(User user) {
    user.setCreatedOn(new Date());
    return userRepository.save(user);
  }

  public User issueBook(Integer userId, List <Book> books) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    List <Book> usersBook = user.getBooks();
    if (user.getDeActivatedOn() == null) {
      for (Book book : books) {
        List <Book> existingBookList = bookRepository.findAll();
        for (Book existingBook : existingBookList) {
          if (existingBook.getId().equals(book.getId())) {
            if (usersBook.contains(existingBook)) {
              System.out.println("This Book is already issued");
            } else {
              existingBook.setIssuedOn(new Date());
              usersBook.add(existingBook);
            }
          } else {
            System.out.println("This book is not availabe in the library");
          }
        }

      }
    } else {
      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
    }

    return userRepository.save(user);
  }

  public User deActivate(Integer userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    if (user.getBooks().isEmpty()) {
      if (user.getDeActivatedOn() == null) {
        user.setDeActivatedOn(new Date());
      } else {
        throw new ResourceNotFoundException("You are Already DeActivated", "Id", userId);
      }
    } else {
      throw new ResourceNotFoundException("Please submit all your books first", "Id", userId);
    }
    return userRepository.save(user);
  }

  public Object activateUser(Integer userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yourself first", "Id", userId));

    if (user.getDeActivatedOn() != null) {
      user.setDeActivatedOn(null);
    } else {
      throw new ResourceNotFoundException("You are Already Activated", "Id", userId);
    }

    return userRepository.save(user);
  }

  public User returnBook(Integer userId, List <Book> books) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    List <Book> usersBook = user.getBooks();
    if (user.getDeActivatedOn() == null) {
      for (Book book : books) {
        List <Book> existingBookList = bookRepository.findAll();
        for (Book existingBook : existingBookList) {
          if (existingBook.getId().equals(book.getId())) {
            if (usersBook.contains(existingBook)) {
              existingBook.setReturnedOn(new Date());
              usersBook.remove(existingBook);

            } else {
              System.out.println("please provide valid book");
            }
          } else {
            System.out.println("please provide valid book");
          }
        }

      }
    } else {
      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
    }

    return userRepository.saveAndFlush(user);
  }
}
