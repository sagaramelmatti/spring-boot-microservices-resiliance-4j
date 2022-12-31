package com.ledzer.invoice.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tran_invoices")
public class Invoice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private Long id;
	
	@Column(unique = true)
    private String uid;

	@Column(name = "invoice_number", precision = 10)
	private Integer invoiceNumber;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "invoice_year", precision = 10)
	private Integer invoiceYear;

	@Column(name = "invoice_type", length = 1)
	private String invoiceType;

	@Column(name = "item_total", precision = 10, scale = 2)
	private Double itemTotal;

	@Column(name = "item_tax", nullable = false, precision = 7, scale = 2)
	private Double itemTax;

	@Column(name = "bill_total", nullable = false, precision = 15, scale = 2)
	private Double billTotal;

	@Column(name = "round_value", nullable = false, precision = 3, scale = 2)
	private Double roundValue;

	@Column(name = "invoice_tax", precision = 15, scale = 2)
	private Double invoiceTax;

	@Column(name = "invoice_total", nullable = false, precision = 15, scale = 2)
	private Double invoiceTotal;

	@Column(name = "invoice_total_word", length = 300)
	private String invoiceTotalWord;

	@Column(name = "amount_paid", nullable = false, precision = 15, scale = 2)
	private Double amountPaid;

	@Column(name = "amount_due", nullable = false, precision = 10, scale = 2)
	private Double amountDue;

	@Column(name = "customer_id", length = 2000)
	private Long customerId;

	@Column(name = "business_id", length = 2000)
	private Long businessId;

	@OneToMany(mappedBy="invoice" , fetch = FetchType.EAGER)
	private Set<InvoiceDetails> invoiceDetails;
	
	 @OneToOne(mappedBy="invoice" , fetch = FetchType.LAZY)
	 private CustomerPaymentDetails customerPaymentDetails;

}
