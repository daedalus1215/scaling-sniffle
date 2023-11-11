package com.example.libraryservice.repository;


import com.example.libraryservice.controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
	
	List<Library> findAllByAuthor(String authorName);

	Library findByName(String bookName);

}
