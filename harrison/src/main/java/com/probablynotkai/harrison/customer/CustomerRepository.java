package com.probablynotkai.harrison.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Optional<Customer> findCustomerByAccountId(Long accountId);
    Optional<Customer> findCustomerByEmail(String email);
}
