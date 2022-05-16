package com.example1.services;

import com.example1.etities.Book;
import com.example1.etities.Shelve;
import com.example1.exception.ResourceNotFoundException;
import com.example1.repositories.BookRepository;
import com.example1.repositories.ShelveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service public class ShelveService {
  @Autowired private ShelveRepository shelveRepository;
  @Autowired private BookRepository bookRepository;

  public Shelve saveShelve(Shelve shelve) {
    List <Book> newBooks = shelve.getBooks();
    for (Book book : newBooks) {
      book.setCreatedOn(new Date());
    }
    shelve.setCreatedOn(new Date());
    return shelveRepository.save(shelve);
  }

  public Shelve saveBook(Integer id, List <Book> books) {
    Shelve existingshelve = shelveRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("This shelve is not available in our library", "Id", id));
    ;
    List <Book> existingList = existingshelve.getBooks();
    for (Book book : books) {
      book.setCreatedOn(new Date());
      existingList.add(book);
    }

    return shelveRepository.save(existingshelve);

  }
}
