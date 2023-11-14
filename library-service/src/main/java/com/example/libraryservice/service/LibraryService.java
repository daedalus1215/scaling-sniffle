package com.example.libraryservice.service;

import com.example.libraryservice.controller.Library;
import com.example.libraryservice.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {

    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    public String buildId(String isbn, String aisle) {
        if (isbn.startsWith("Z")) {
            return "OLD" + isbn + aisle;
        }

        return isbn + aisle;
    }

    public boolean checkBookAlreadyExist(String id) {
        Optional<Library> lib = repository.findById(id);
        return lib.isPresent();
    }

    public Library getBookById(String id) {
        return repository.findById(id).get();
    }
}
