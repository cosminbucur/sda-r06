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

        // read
        List<Book> foundBooks = dao.findAll();
        int idOfFirstBook = foundBooks.get(0).getId();

        // print found books
        foundBooks.forEach(book -> System.out.println(book));

        // update
        Book newBookData = new Book("test", "updated");
        dao.update(idOfFirstBook, newBookData);

        // delete
        dao.delete(idOfFirstBook);

        dao.findAll().forEach(book -> System.out.println(book));
    }
}
