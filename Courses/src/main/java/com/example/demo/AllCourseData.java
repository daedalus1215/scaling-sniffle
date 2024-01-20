package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Storage1")
public class AllCourseData implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "price")
    private int price;
    @Column(name = "category")
    private String category;

    public AllCourseData(String id, String courseName, int price, String category) {
        this.id = id;
        this.courseName = courseName;
        this.price = price;
        this.category = category;
    }

    public AllCourseData() {

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String course_name) {
        this.courseName = course_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
