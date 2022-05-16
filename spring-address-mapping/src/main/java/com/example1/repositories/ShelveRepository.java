package com.example1.repositories;

import com.example1.etities.Book;
import com.example1.etities.Shelve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelveRepository extends JpaRepository <Shelve, Integer> {
}
