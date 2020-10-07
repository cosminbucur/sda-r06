package com.sda.hibernate.audit.customer;

public interface CustomerRepository {

    void create(Customer customer);

    Customer findById(Long id);

    Customer update(Long id, Customer customerDetails);

    void delete(Long id);
}
