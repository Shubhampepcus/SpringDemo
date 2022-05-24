package com.example1.services;

import com.example1.etities.Book;
import com.example1.etities.User;
import com.example1.exception.ResourceNotFoundException;
import com.example1.repositories.BookRepository;
import com.example1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.Date;
import java.util.List;

@Service public class UserService {
  @Autowired private BookRepository bookRepository;
  @Autowired private UserRepository userRepository;

  public User createUser(User user) {
    user.setCreatedOn(new Date());
    return userRepository.save(user);
  }

  public String issueBook(Integer userId, List <Book> books) {
    String result = null;
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    List <Book> usersBook = user.getBooks();

    if (user.getDeActivatedOn() == null) {
      for (Book book : books) {
        if (bookRepository.existsById(book.getId())) {
          Book availableBook = bookRepository.getById(book.getId());
          if (availableBook.getIssuedOn() == null) {
            if (usersBook.contains(availableBook)) {
              result = "This Book is already issued : "+ availableBook.getId();
            } else {
//              availableBook.setUser(user);
              availableBook.setIssuedOn(new Date());
              usersBook.add(availableBook);
              result = "Book is Successfully issued : "+ availableBook.getId();

            }
          } else {
            result = "This book is already issued by another user : "+availableBook.getId();
          }
        } else {
          result = "This book is not available in the library : "+book.getId();
        }
      }
    } else {
      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
    }
    userRepository.save(user);
    return result;
  }

  //        } else{
  //          System.out.println("this book is not availabe in the library");
  //        }
  //        List <Book> existingBookList = bookRepository.findAll();
  //        for (Book existingBook : existingBookList) {
  //          if (existingBook.getId().equals(book.getId())) {
  //            if (usersBook.contains(existingBook)) {
  //              System.out.println("This Book is already issued");
  //            } else {
  //              existingBook.setIssuedOn(new Date());
  //              usersBook.add(existingBook);
  //            }
  //          } else {
  //            System.out.println("This book is not availabe in the library");
  //          }
  //        }
  //
  //      }
  //    } else {
  //      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
  //    }

  //    return userRepository.save(user);
  //  }

  public String deActivate(Integer userId) {
    String result = null;
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    if (user.getBooks().isEmpty()) {
      if (user.getDeActivatedOn() == null) {
        user.setDeActivatedOn(new Date());
        result = "You are successfully DeActivated : "+userId;
      } else {
        throw new ResourceNotFoundException("You are Already DeActivated", "Id", userId);
      }
    } else {
      throw new ResourceNotFoundException("Please submit all your books first", "Id", userId);
    }

    userRepository.save(user);
    return result;
  }

  public String activateUser(Integer userId) {
    String result;
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yourself first", "Id", userId));

    if (user.getDeActivatedOn() != null) {
      user.setDeActivatedOn(null);
      result = "You are successfully Activated : "+ userId;
    } else {
      throw new ResourceNotFoundException("You are Already Activated", "Id", userId);
    }

    userRepository.save(user);
    return result;
  }

  public String returnBook(Integer userId, List <Book> books) {
    String result = null;
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
    List <Book> usersBook = user.getBooks();

    if (user.getDeActivatedOn() == null) {
      for (Book book : books) {
        if (bookRepository.existsById(book.getId())) {
          Book availableBook = bookRepository.getById(book.getId());
          if (availableBook.getIssuedOn() != null) {
            if (usersBook.contains(availableBook)) {
//              availableBook.setUser(null);
              availableBook.setReturnedOn(new Date());
              availableBook.setIssuedOn(null);
              usersBook.remove(availableBook);
             result = "Book is Successfully returned : "+ availableBook.getId();

            } else {
             result = "This Book is not issued by you : "+ availableBook.getId();
            }
          } else {
           result = "Please provide a valid book : "+ availableBook.getId();
          }
        } else {
         result = "Please provide a valid book : "+ book.getId();
        }
      }
    } else {
      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
    }
    userRepository.saveAndFlush(user);
    return result;
  }

  //    User user = userRepository.findById(userId)
  //        .orElseThrow(() -> new ResourceNotFoundException("Plsease Register yoursef first", "Id", userId));
  //    List <Book> usersBook = user.getBooks();
  //    if (user.getDeActivatedOn() == null) {
  //      for (Book book : books) {
  //        List <Book> existingBookList = bookRepository.findAll();
  //        for (Book existingBook : existingBookList) {
  //          if (existingBook.getId().equals(book.getId())) {
  //            if (usersBook.contains(existingBook)) {
  //              existingBook.setUser(null);
  //              existingBook.setReturnedOn(new Date());
  //              usersBook.remove(existingBook);
  //
  //            } else {
  //              System.out.println("please provide valid book");
  //            }
  //          } else {
  //            System.out.println("please provide valid book");
  //          }
  //        }
  //
  //      }
  //    } else {
  //      throw new ResourceNotFoundException("Please Register yourself first", "Id", userId);
  //    }
  //
  //    return userRepository.saveAndFlush(user);
  //  }
}
