package br.com.customer.sales.repository;

import br.com.customer.sales.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
