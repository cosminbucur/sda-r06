package com.sda.hibernate.audit;

import com.sda.hibernate.audit.customer.Customer;
import com.sda.hibernate.audit.customer.CustomerHibernateRepository;
import com.sda.hibernate.audit.customer.CustomerRepository;

public class DemoAudit {

    public static void main(String[] args) {
        // create customer
        Customer customer = new Customer();
        customer.setFirstName("jon");
        customer.setLastName("snow");

        CustomerRepository repository = new CustomerHibernateRepository();
        // save - inserted
        repository.create(customer);

        // find
        Customer foundCustomer = repository.findById(1L);

        // update - updated
        foundCustomer.setFirstName("alex");
        foundCustomer.setLastName("vasile");

        repository.update(foundCustomer.getId(), foundCustomer);
        Customer updatedCustomer = repository.findById(1L);

        // delete - deleted
        repository.delete(updatedCustomer.getId());
    }
}
