package codegym.repository;

import codegym.model.Customer;

public interface ICustomerRepository {
    boolean insertWithStoredProcedure(Customer customer);

    Customer findById(int id);
}
