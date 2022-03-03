package codegym.service;

import codegym.model.Customer;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByFirstNameContaining(String firstName);
}
