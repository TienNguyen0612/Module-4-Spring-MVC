package codegym.repository;

import codegym.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomerRepositoryImpl implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        String sql = "CALL Insert_Customer(:first_name, :last_name)";
        Query query = entityManager.createNativeQuery(sql, Customer.class);
        query.setParameter("first_name", customer.getFirstName());
        query.setParameter("last_name", customer.getLastName());
        int result = query.executeUpdate();
        System.out.println(result);
        return result == 0;
    }

    @Override
    public Customer findById(int id) {
        String sql = "CALL find(:id_find)";
        Query query = entityManager.createNativeQuery(sql, Customer.class);
        query.setParameter("id_find", id);
        return (Customer) query.getSingleResult();
    }


}
