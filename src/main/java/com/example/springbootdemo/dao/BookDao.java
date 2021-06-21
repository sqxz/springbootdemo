package com.example.springbootdemo.dao;

import com.example.springbootdemo.pojo.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookDao {
    @Select("select * from books")
    List<Book> getAllBooks();

    @Select("select * from books where bookID = #{bookID}")
    Book getBookById(@Param("bookID") int bookID);

    @Delete("delete from books where bookID = #{bookID}")
    void deleteBookById(@Param("bookID") int bookID);

    @Insert("insert into books (bookID,bookName,bookCounts,detail) values (#{bookID},#{bookName},#{bookCounts},#{detail})")
    void addBook(Book book);

    @Update("update books set bookName = #{bookName} where bookID = #{bookID}")
    void updateBook(int bookID,String bookName);
}
