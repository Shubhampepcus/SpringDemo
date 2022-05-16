package com.example1.services;

import com.example1.etities.Book;
import com.example1.etities.User;
import com.example1.exception.ResourceNotFoundException;
import com.example1.repositories.BookRepository;
import com.example1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Service
public class UserService  {
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private UserRepository userRepository;

  public User createUser(User user){
    user.setCreatedOn(new Date());
    return userRepository.save(user);
  }

  public User issueBook(Integer userId, List<Book> books) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    List<Book> usersBook = user.getBooks();
    if(user.getDeActivatedOn()==null){
      for (Book book: books) {
//        List<Book> existingBookList = bookRepository.findAll();
        Book existingBook = bookRepository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("This book is not available in our library", "Id", book.getId()));
//        if(existingBookList.contains(book)){
//
//        }
        if (usersBook.contains(existingBook)) {
          throw new ResourceNotFoundException("You have already issued this book", "Id", existingBook.getId());
        } else {
          usersBook.add(existingBook);
        }
      }
    } else {
      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
    }
    return userRepository.save(user);
  }

  public User deActivate(Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));

    if(user.getDeActivatedOn()==null){
      user.setDeActivatedOn(new Date());
    } else {
      throw new ResourceNotFoundException("You are Already DeActivated", "Id", userId);
    }
    return userRepository.save(user);
  }

  public Object activateUser(Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    if(user.getDeActivatedOn()!=null){
      user.setDeActivatedOn(null);
    } else {
      throw new ResourceNotFoundException("You are Already Activated", "Id", userId);
    }

    System.out.println("git fetch && git checkout <<branch_name>>");
    return userRepository.save(user);
  }
}
