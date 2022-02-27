package com.codingworld.resiliencedemo.controller;

import com.codingworld.resiliencedemo.service.Book;
import com.codingworld.resiliencedemo.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

@Autowired
  BookService service;

  @GetMapping("/allbook")
  public Book[] getAllBook() {

    return  service.getAllBook();
  }

  @GetMapping("/saveBook")
  public Book addBook() {
   return service.addBook();
  }
}
