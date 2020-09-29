package com.sda.jdbc;

import java.util.List;

public class DemoStatements {

    public static void main(String[] args) {
        // CRUD db
        // create
        Book book1 = new Book("game of thrones", "george martin");
        Book book2 = new Book("karamazov brothers", "dostoievski");

        BookJdbcDao dao = new BookJdbcDao();
        dao.create(book1);
        dao.create(book2);

        // id, title, author
        // 1, game of thrones, george martin
        // insert into book values(....)

        // read
        List<Book> foundBooks = dao.findAll();

        // print found books
        foundBooks.forEach(book -> System.out.println(book));

        // update
        // delete
    }
}
