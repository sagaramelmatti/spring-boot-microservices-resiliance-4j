package com.ledzer.invoice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ledzer.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

	@Query(value = "SELECT max(invoiceNumber) FROM Invoice")
	Long getInvoiceNoSeq();
	
	@Query(value = "SELECT max(id) FROM Invoice")
	Long getInvoiceId();
	
	Invoice findByUid(String uid);

	List<Invoice> findByCustomerId(long parseLong);

}