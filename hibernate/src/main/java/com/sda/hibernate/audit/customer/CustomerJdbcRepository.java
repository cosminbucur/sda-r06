package com.sda.hibernate.audit.customer;

public class CustomerJdbcRepository implements CustomerRepository {

    @Override
    public void create(Customer customer) {

    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public Customer update(Long id, Customer customerDetails) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
