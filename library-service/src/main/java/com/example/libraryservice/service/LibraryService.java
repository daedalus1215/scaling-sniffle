package com.example.libraryservice.service;

import com.example.libraryservice.controller.Library;
import com.example.libraryservice.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository repository;

    public String buildId(String isbn, int aisle) {
        if (isbn.startsWith("Z")) {
            return "OLD" + isbn + aisle;
        }

        return isbn + aisle;
    }

    public boolean checkBookAlreadyExist(String id) {
        Optional<Library> lib = repository.findById(id);
        if (lib.isPresent())
            return true;
        else
            return false;


    }

    public Library getBookById(String id) {
        return repository.findById(id).get();
    }
}
