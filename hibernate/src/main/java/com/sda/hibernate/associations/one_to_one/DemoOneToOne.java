package com.sda.hibernate.associations.one_to_one;

public class DemoOneToOne {

    public static void main(String[] args) {
        // employee
        Employee employee = new Employee();
        employee.setName("jonsnow");
        employee.setEmail("jonsnow@gmail.com");

        // account
        Account account = new Account();
        account.setName("123");
        // set employee on account
        account.setEmployee(employee);

        // set account on employee
        employee.setAccount(account);

        // save account
        AccountDao accountDao = new AccountDao();
        accountDao.create(account);

        // expect 2 inserts
    }
}
