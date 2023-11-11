package com.example.libraryservice.controller;

import org.springframework.stereotype.Component;

@Component
public class ProductsPrices {


    long booksPrice;
    long coursesPrice;

    public long getBooksPrice() {
        return booksPrice;
    }

    public void setBooksPrice(long booksPrice) {
        this.booksPrice = booksPrice;
    }

    public long getCoursesPrice() {
        return coursesPrice;
    }

    public void setCoursesPrice(long coursesPrice) {
        this.coursesPrice = coursesPrice;
    }

}
