package com.ledzer.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
