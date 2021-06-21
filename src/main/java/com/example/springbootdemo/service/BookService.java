package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.BookDao;
import com.example.springbootdemo.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;
    public List<Book> getAllBook(){
       return bookDao.getAllBooks();
    }

    public Book getBookById(int bookID){
        return bookDao.getBookById(bookID);
    }

    public void deleteBookById(int bookID){
        bookDao.deleteBookById(bookID);
    }

    public void addBook(Book book){
        bookDao.addBook(book);
    }

    public void updateBook(int bookID,String bookName){
        bookDao.updateBook(bookID,bookName);
    };
}
