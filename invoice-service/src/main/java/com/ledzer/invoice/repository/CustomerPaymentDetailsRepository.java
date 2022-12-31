package com.ledzer.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.invoice.entity.CustomerPaymentDetails;

public interface CustomerPaymentDetailsRepository extends JpaRepository<CustomerPaymentDetails, Long> {

}