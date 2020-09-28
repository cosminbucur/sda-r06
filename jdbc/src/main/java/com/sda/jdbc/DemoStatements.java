package com.sda.jdbc;

public class DemoStatements {

    public static void main(String[] args) {
        // CRUD db
        // create
        Book book = new Book("game of thrones", "george martin");

        BookJdbcDao dao = new BookJdbcDao();
        dao.create(book);

        // id, title, author
        // 1, game of thrones, george martin
        // insert into book values(....)

        // read
        // update
        // delete
    }
}
