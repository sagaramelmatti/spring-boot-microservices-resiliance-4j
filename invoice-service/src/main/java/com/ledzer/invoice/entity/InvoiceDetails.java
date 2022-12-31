package com.ledzer.invoice.entity;

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
@Table(name="tran_invoice_details")
public class InvoiceDetails {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Long id;
	
	@Column(name="hsn_code", length=20)
    private String hsnCode;
    
	@Column(name="product_id")
    private Long productId;
    
    @Column(precision=10, scale=2)
    private Double rate;
    
    @Column(precision=10)
    private Integer quantity;
    
    @Column(name="taxable_amount", precision=10, scale=2)
    private Double taxableAmount;
    
    @Column(name="sgst_per", precision=5, scale=2)
    private Double sgstPer;
    
    @Column(name="sgst_amt", precision=10, scale=2)
    private Double sgstAmt;
    
    @Column(name="cgst_per", precision=5, scale=2)
    private Double cgstPer;
    
    @Column(name="cgst_amt", precision=10, scale=2)
    private Double cgstAmt;
    
    @Column(name="igst_per", precision=5, scale=2)
    private Double igstPer;
    
    @Column(name="igst_amt", precision=10, scale=2)
    private Double igstAmt;
	
    @ManyToOne(optional=false)
    @JoinColumn(name="invoice_id", nullable=false)
    private Invoice invoice;
    
    
	
	//@ManyToOne
    //@JoinColumn(name="product_id")
    //private Product product;
	
}
