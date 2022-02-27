package com.codingworld.resiliencedemo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

  private RestTemplate restTemplate= new RestTemplate();


  @CircuitBreaker(name = "myCircuitBreaker",fallbackMethod = "getDefaultBook")
  public Book[] getAllBook() {
    String url = "http://localhost:8000/book/all";
    return restTemplate.getForObject(url, Book[].class);
  }

  private Book[] getDefaultBook(Exception e) {
    System.out.println("coming into fallBack method and returning result.");
    Book[] books ={};
    return  books;
  }


  @RateLimiter(name = "saveBook",fallbackMethod = "skipBookEntry")
  public Book addBook() {
    String url = "http://localhost:8000/book/save";
    Book newBook=new Book(1,"DOEPICSHIT",100d);
    HttpEntity<Book> request = new HttpEntity<>(newBook);
    Book book = restTemplate.postForObject(url, request, Book.class);
    System.out.println("Booked Saved");
    return newBook;
  }
  public Book skipBookEntry(Throwable throwable) {
    System.out.println("Book Save Entry has been skipped");
    return new Book();
  }
}
