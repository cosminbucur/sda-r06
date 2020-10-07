package com.sda.hibernate.audit.history;

import com.sda.hibernate.audit.customer.Customer;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.time.LocalDateTime;

public class CustomerHistoryListener {

    private CustomerHistoryRepository repository;

    public CustomerHistoryListener() {
        this.repository = new CustomerHistoryRepository();
    }

    @PostPersist
    public void postPersist(Customer customer) {
        perform(customer, Action.INSERTED);
    }

    @PostUpdate
    public void postUpdate(Customer customer) {
        perform(customer, Action.UPDATED);
    }

    @PostRemove
    public void postRemove(Customer customer) {
        perform(customer, Action.DELETED);
    }

    private void perform(Customer customer, Action action) {
        CustomerHistory history = new CustomerHistory();
        history.setAction(action);
        history.setCreationDate(LocalDateTime.now());
        history.setCustomerId(customer.getId());
        history.setModifiedBy("current user");

        repository.create(history);
    }
}
