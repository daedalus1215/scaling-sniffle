package com.example.libraryservice.repository;

import com.example.libraryservice.controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String> {
    @Query(value = "SELECT * FROM storage2 as b WHERE b.author = :authorName", nativeQuery = true)
    List<Library> findAllByAuthor(@Param("authorName") String authorName);

    @Query(value = "SELECT * FROM storage2 as b WHERE b.book_name = :bookName", nativeQuery = true)
    Library findByName(@Param("bookName") String bookName);
}
