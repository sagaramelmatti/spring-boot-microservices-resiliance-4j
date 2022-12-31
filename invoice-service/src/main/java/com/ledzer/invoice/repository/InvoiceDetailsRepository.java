package com.ledzer.invoice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.invoice.entity.InvoiceDetails;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails,Long> {


}