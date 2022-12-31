package com.ledzer.invoice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="tran_customer_payment_details")
public class CustomerPaymentDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Long id;
	
    @Column(name="payment_date", nullable=false)
    private Date paymentDate;
    
    @Column(name="payment_amount", nullable=false, precision=10, scale=2)
    private Double paymentAmount;
    
    @Column(name="payment_type", length=2)
    private String paymentType;
    
	@Column(name="customer_id")
    private Long customerId;
    
    @Column(name="business_id")
    private Long businessId;
    
    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;
	

}
