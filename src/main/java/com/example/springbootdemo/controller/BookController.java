package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.ApiResp;
import com.example.springbootdemo.pojo.Book;
import com.example.springbootdemo.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @ApiOperation(value = "查询所有书籍")
    @GetMapping("/allBook")
    public ApiResp getAllBooks(){ return ApiResp.ok(bookService.getAllBook());}

    @ApiOperation(value = "通过id查询书籍")
    @GetMapping("/getBookById")
    public ApiResp getBookById(int bookID){
        return ApiResp.ok(bookService.getBookById(bookID));
    }

    @ApiOperation(value = "删除书籍")
    @PutMapping("/delete/{bookId}")
    public ApiResp deleteBookById(@PathVariable(value = "bookId") int bookID){
        bookService.deleteBookById(bookID);
        return ApiResp.ok();
    }

    @ApiOperation(value = "增加书籍")
    @PostMapping("/addBook")
    public ApiResp addBook(Book book){
        bookService.addBook(book);
        return ApiResp.ok();
    }

    @ApiOperation(value = "修改书籍")
    @PutMapping("/updateBook/{bookId}")
    public ApiResp updateBook(@PathVariable(value = "bookId") int bookId,String bookName){
        bookService.updateBook(bookId,bookName);
        return ApiResp.ok();
    }

}
