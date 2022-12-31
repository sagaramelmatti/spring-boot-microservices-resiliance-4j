import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import $ from 'jquery';
import moment from 'moment';
import InvoiceDataService from '../../services/InvoiceDataService';

function AddInvoice(props) {

    let date = new Date()

    const [customerId, setCustomerId] = useState("");
    const [businessId, setBusinessId] = useState("1");

    const [id, setId] = useState("");
    
    const [invoiceId, setInvoiceId] = React.useState([]);
    const [name, setName] = React.useState([]);
    const [customers, setCustomers] = React.useState([]);
    const [products, setProducts] = React.useState([]);
    const [invoiceNumber, setInvoiceNumber] = React.useState([]);
    const [invoiceType, setInvoiceType] = React.useState("P");
    const [invoiceDate, setInvoiceDate] = React.useState(moment(new Date()).format("DD/MM/YYYY"));
    const [isGstIncluded, setIsGstIncluded] = React.useState("N");
    
    const [invoiceInitial, setInvoiceInitial] = React.useState("");
    const [invoiceNumberLabel, setInvoiceNumberLabel] = React.useState("");
    const [amountPaid, setAmountPaid] = React.useState(0.00);
    const [amountDue, setAmountDue] = React.useState(0.00);
    const [itemTotal, setItemTotal] = React.useState(0.00);
    const [itemTax, setItemTax] = React.useState(0.00);
    const [billTotal, setBillTotal] = React.useState(0.00);
    const [roundValue, setRoundValue] = React.useState(0.00);
    const [invoiceTotal, setInvoiceTotal] = React.useState(0.00);
    const [totalOutBal, setTotalOutBal] = React.useState(0.00);
    

    /* Table column */
    const [rate, setRate] = React.useState(0.00);
    const [ratePre, setRatePre] = React.useState(0.00);
    const [quantity, setQuantity] = React.useState(1);
    const [quantityPre, setQuantityPre] = React.useState(0);
    const [productId, setProductId] = useState("");
    const [rowCounter, setRowCounter] = useState(1);
    const [hsnCode, setHsnCode] = useState("");
    const [description, setDescription] = useState("");
    const [gst, setGst] = useState(0.00);
    const [gstPre, setGstPre] = useState(0.00);
    const [gstAmt, setGstAmt] = useState(0.00);
    const [gstAmtPre, setGstAmtPre] = useState(0.00);
    const [actionFlag, setActionFlag] = useState("");
    
    const [paymentNumber, setPaymentNumber] = useState("");
    const [paymentVoucher, setPaymentVoucher] = useState("");
    const [receiptNumber, setReceiptNumber] = useState("");
    const [receiptName, setReceiptName] = useState("");
    

    React.useEffect(() => {
        getInvoiceNumber();
        getCuctomers();
        getProducts();
        getCustomerPaymentNumber();

    }, []);

    function handleInvoiceDate(event)
    {
        let invoiceDate = moment(new Date()).format("DD/MM/YYYY");
        setInvoiceDate(invoiceDate);
    }

    async function getCuctomers() {
        const response = await fetch("http://localhost:9191/api/customers/list");
        const body = await response.json();
        setCustomers(body.map(customer => {
            return { value: customer.id, label: customer.name };
        }));
    }


    async function getProducts() {
        const response = await fetch("http://localhost:9191/api/invoices/products");
        const body = await response.json();
        setProducts(body.map(product => {
            return { value: product.id, label: product.name };
        }));
    }

    const getInvoiceNumber = () => {
        axios
            .get("http://localhost:9191/api/invoices/invoiceNoSeq")
            .then((response) => {
                if (response.status === 200) {
                    setInvoiceNumber(response?.data);
                    
                        axios
                            .get("http://localhost:9191/api/invoices/initial")
                            .then((response2) => {
                                if (response2.status === 200) {
                                    setInvoiceInitial(response2?.data);
                                    let invoiceName = response2?.data + '00' + response?.data;
                                    setName(invoiceName);
                                }
                            })
                            .catch((error) => {
                                console.log(error);
                            });

                            axios
                            .get("http://localhost:9191/api/invoices/invoiceId")
                            .then((response2) => {
                                if (response2.status === 200) {
                                    setInvoiceId(response2?.data);
                                }
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                            
                }
            })
            .catch((error) => {
                console.log(error);
            });
    };


    const getCustomerPaymentNumber = () => {
        axios
            .get("http://localhost:9191/api/invoices/paymentNumber")
            .then((response) => {
                if (response.status === 200) {
                    setPaymentNumber(response?.data);
                    setPaymentVoucher('CU-PYMT000'+response?.data);

                    axios
                    .get("http://localhost:9191/api/invoices/receiptNumber")
                    .then((response2) => {
                        if (response2.status === 200) {
                            setReceiptNumber(response2?.data);
                            let receiptName = '000' + response2?.data;
                            setReceiptName(receiptName);
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });


                }
            })
            .catch((error) => {
                console.log(error);
            });
    };

    function handleProductIdChange(event) {
        event.preventDefault();
        setProductId(event.target.value);

        fetch("http://localhost:9191/api/invoices/products/"+event.target.value)
        .then(response => response.json())
        .then(data => {
            setRate(data.rate);
            setGst(data.gstPer);
            let gstAmtValue = ( data.rate / 100 ) * data.gstPer;
            setGstAmt(gstAmtValue);
            if(gstAmtValue !== 0)
            {
                /* Calculate Taxable Amt */
                let taxableAmount = rate * quantity;
                let taxableAmounPre = ratePre * quantityPre;
                let itemTotal = taxableAmount - taxableAmounPre;
                setItemTotal(itemTotal);
                let singleItemTax = (parseFloat(taxableAmount) / 100) * parseFloat(gst);
                let singleItemTaxPre = (parseFloat(taxableAmounPre) / 100) * parseFloat(gstPre);
                let itemTaxValue = (itemTax - singleItemTaxPre) + parseFloat(singleItemTax);
                setItemTax(itemTaxValue);
                let billTotal = parseFloat(itemTotal) + parseFloat(itemTaxValue);
                setBillTotal(billTotal);
                setInvoiceTotal(billTotal);
                setAmountPaid(billTotal);
            }
        })
    }

    function handleRateChange(event) {
        event.preventDefault()
        setRate(event.target.value);

        /* Calculate Taxable Amt */
        let taxableAmount = event.target.value * quantity;
        let taxableAmounPre = ratePre * quantityPre;
        let itemTotal = taxableAmount - taxableAmounPre;
        setItemTotal(itemTotal);
        let singleItemTax = (parseFloat(taxableAmount) / 100) * parseFloat(gst);
        let singleItemTaxPre = (parseFloat(taxableAmounPre) / 100) * parseFloat(gstPre);
        let itemTaxValue = (itemTax - singleItemTaxPre) + parseFloat(singleItemTax);
        setItemTax(itemTaxValue);
        let billTotal = parseFloat(itemTotal) + parseFloat(itemTaxValue);
        setBillTotal(billTotal);
        setInvoiceTotal(billTotal);
        setAmountPaid(billTotal);
    }


    function handleQuantityChange(event) {
        event.preventDefault()
        setQuantity(event.target.value);

        /* Calculate Taxable Amt */
        let taxableAmount = rate * event.target.value;
        let taxableAmounPre = ratePre * quantityPre;
        let itemTotal = taxableAmount - taxableAmounPre;
        setItemTotal(itemTotal);
        let singleItemTax = (parseFloat(taxableAmount) / 100) * parseFloat(gst);
        let singleItemTaxPre = (parseFloat(taxableAmounPre) / 100) * parseFloat(gstPre);
        let itemTaxValue = (itemTax - singleItemTaxPre) + parseFloat(singleItemTax);
        setItemTax(itemTaxValue);
        let billTotal = parseFloat(itemTotal) + parseFloat(itemTaxValue);
        setBillTotal(billTotal);
        setInvoiceTotal(billTotal);
        setAmountPaid(billTotal);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        var data = {
            id : invoiceId,
            invoiceNumber : invoiceNumber,
            name : name,
            invoiceType : invoiceType,
            invoiceDate : invoiceDate,
            customerId : customerId,
            businessId : businessId,
            itemTotal : itemTotal,
            itemTax : itemTax,
            billTotal : billTotal,
            roundValue : roundValue,
            invoiceTotal : invoiceTotal,
            amountPaid : amountPaid,
            amountDue : amountDue,
            isGstIncluded : isGstIncluded,
            paymentNumber : paymentNumber,
            paymentVoucher : paymentVoucher,
            receiptNumber : receiptNumber,
            receiptName : receiptName,
            invoiceDetailsDTOList : [
                {
                    productId: productId,
                    description : description,
                    hsnCode : hsnCode,
                    rate : rate,
                    quantity : quantity,
                    gst : gst,
                    gstAmt : gstAmt,
                },
            ]
                
        };

        InvoiceDataService.create(data)
          .then(response => {
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }

    return (
        <div>

            <div className="content-wrapper">
                <section className="content-header">
                    <h1>
                        <div className="col-xs-12">
                            Add Invoice
                        </div>
                    </h1>
                </section>
                <section className="invoice">
                    <form method ="POST" className='form' onSubmit={handleSubmit}>
                        <div id="myModal" className="modal fade" data-keyboard="false" data-backdrop="static">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-body">
                                        <div className="alert alert-danger">
                                            <h4><i className="icon fa fa-ban"></i> Alert!</h4>
                                            <strong>Error!</strong> There Must Be Atleast One Row In Table, You Cannot Delete All Rows
                                        </div>
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" id="dismiss" data-dismiss="modal" className="btn btn-primary">Ok</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-xs-3">
                                <small>Customer Name</small>
                                <address>
                                    <div className="form-group">
                                        <select className='form-control select2' value={customerId} onChange={(e) => setCustomerId(e.target.value)}>
                                            {customers.map(o => (
                                                <option key={o.value} value={o.value}>{o.label}</option>
                                            ))}
                                        </select>
                                        <input className="form-control" type="text" id="invoiceId" name="invoiceId" value={invoiceId} onChange={(e) => setInvoiceId(e.target.value)} />
                                        <input className="form-control input-sm" type="hidden" name="customer_state_id" id="customer_state_id" value="27" placeholder="Total Paid" />
                                        <input className="form-control input-sm" type="hidden" id="invoiceType" name="invoiceType" value={invoiceType} onChange={(e) => setInvoiceType(e.target.value)} />
                                        <input className="form-control input-sm" type="text" name="name" id="name" placeholder="Name" value={name} />
                                        <input className="form-control" type="text" id="invoiceNumber" name="invoiceNumber" value={invoiceNumber} onChange={(e) => setInvoiceNumber(e.target.value)} />
                                        
                                        <input className="form-control" type="text" id="invoiceInitial" name="invoiceInitial" value={invoiceInitial} onChange={(e) => setInvoiceInitial(e.target.value)} />
                                        <input className="form-control input-sm" type="hidden" name="customer_gstin" id="customer_gstin" placeholder="Enter GSTIN If Available " />
                                        <input className="form-control input-sm" type="hidden" name="totalOutBal" id="totalOutBal" placeholder="Current Out Balance" value={totalOutBal} onChange={(e) => setTotalOutBal(e.target.value)} />
                                        <input className="form-control input-sm" type="hidden" name="total_amt_paid" id="total_amt_paid" placeholder="Total Paid" value="0.00" />
                                        <input type="text"  id="paymentNumber" placeholder="Payment Number" value={paymentNumber} onChange={(e) => setPaymentNumber(e.target.value)} />
                                        <input type="text"  id="paymentVoucher" placeholder="Payment Number" value={paymentVoucher} onChange={(e) => setPaymentVoucher(e.target.value)} />
                                        <input  type="text" id="receiptNumber" name="receiptNumber" value={receiptNumber} onChange={(e) => setReceiptNumber(e.target.value)} />
                                        <input type="text" id="receiptName" name="receiptName" value={receiptName} onChange={(e) => setReceiptName(e.target.value)} />
                                        <input type="hidden" id="totalRows" name="totalRows" value="1" />
                                        <input type="hidden" id="currentRowCount" name="currentRowCount" value="1" />
                                    </div>
                                </address>
                            </div>
                            <div className="col-xs-1">
                            </div>
                            <div className="col-xs-2">
                                <small>GST Included </small><br />
                                <label id="gstlabel">
                                    <input type="radio" className="flat-red" name="isGstIncluded" id="gst_included_yes" value="Y" onChange={(e) => setIsGstIncluded(e.target.value)} /> &nbsp;&nbsp;
                                    Yes &nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" className="flat-red" name="isGstIncluded" id="gst_included_no" value="N" checked={true} onChange={(e) => setIsGstIncluded(e.target.value)} /> &nbsp;&nbsp;
                                    No
                                </label>
                            </div>
                            <div className="col-xs-2">
                                <small> Invoice Number</small>
                                <input className="form-control" type="text" name="invoiceNumberLabel" id="invoiceNumberLabel" value={invoiceInitial + '00' + invoiceNumber} onChange={(e) => setInvoiceNumberLabel(e.target.value)} width="100" />
                            </div>
                            <div className="col-xs-2">
                                <small className="pull-right">Invoice Date: <input type="text" className="form-control pull-right" id="invoiceDate" name="invoiceDate" required placeholder="Enter Invoice Date" value={invoiceDate} onChange={handleInvoiceDate} /></small>
                                <input className="form-control" type="hidden" name="business_state_id" id="business_state_id" value="" />
                                <input className="form-control" type="hidden" name="businessId" id="businessId" value={businessId} onChange={(e) => setBusinessId(e.target.value)} />
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-xs-12 table-responsive">
                                <table className="table table-striped table-bordered dataTable" id="editable-sample" aria-describedby="editable-sample_info">
                                    <tbody>
                                        <tr>
                                            <th width="5%">S.No</th>
                                            <th width="20%">Product</th>
                                            <th width="30%">Description</th>
                                            <th width="10%">Rate</th>
                                            <th width="10%">Quantity</th>
                                            <th width="10%">Gst %</th>
                                            <th width="10%">GST Amt</th>
                                            <th width="5%">Delete</th>
                                        </tr>

                                        <tr className="tr_clone">
                                            <td className="sorting_1">
                                                <input type="text" name="rowCounter[]" className="rowCounter" id="rowCounter" value={rowCounter} onChange={(e) => setRowCounter(e.target.value)} />
                                            </td>
                                            <td className="" >
                                                <select className='form-control select2' value={productId} onChange={(e) => handleProductIdChange(e)} >
                                                    {products.map(o => (
                                                        <option key={o.value} value={o.value}>{o.label}</option>
                                                    ))}
                                                </select>
                                                <input type="hidden" name="hsnCode[]" className="form-control input-sm smallsmallnumber" id="hsnCode" value={hsnCode} onChange={(e) => setHsnCode(e.target.value)} />
                                            </td>
                                            <td className=""><input type="text" className="form-control input-sm number" placeholder="Description" name="description[]" id="description" value={description} onChange={(e) => setDescription(e.target.value)} /></td>
                                            <td className=""> <input type="text" required="" className="form-control input-sm smallnumber" placeholder="Rate" name="rate[]" id="rate" value={rate} onChange={handleRateChange} /><input type="hidden" name="ratePre[]" id="ratePre" value={ratePre} onChange={(e) => setRatePre(e.target.value)} /></td>
                                            <td className=""><input type="text" required="" className="form-control input-sm smallsmallnumber" placeholder="Quantity" name="quantity[]" value={quantity} onChange={handleQuantityChange} /><input type="hidden" name="quantityPre[]" id="quantityPre" value={quantityPre} onChange={(e) => setQuantityPre(e.target.value)} /></td>
                                            <td className="">  <input type="text" className="form-control input-sm smallnumber" placeholder="GST %" name="gst[]" id="gst" value={gst} onChange={(e) => setGst(e.target.value)} /><input type="hidden" className="form-control" placeholder="GST %" name="gstPre[]" id="gstPre" value={gstPre} onChange={(e) => setGstPre(e.target.value)} /></td>
                                            <td className=""><input type="text" className="form-control input-sm smallnumber" placeholder="GST Amt" name="gstAmt[]" id="gstAmt" value={gstAmt} onChange={(e) => setGstAmt(e.target.value)} /><input type="hidden" className="form-control input-sm smallsmallnumber" placeholder="GST Amount" name="gstAmtPre[]" id="gstAmtPre" value={gstAmtPre} onChange={(e) => setGstAmtPre(e.target.value)} />
                                                <input type="hidden" name="actionFlag[]" className="form-control input-sm smallsmallnumber" id="actionFlag" value="A" /></td>
                                            <td className=""><button type="button" className="btn btn-danger cancelBtn" name="cancelBtn[]" id="cancelBtn"><i className="fa fa-trash-o"></i></button></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-xs-2">
                                <br /><br />
                                <button type="button" id="addmore" className="btn btn-primary" >
                                    <i className="fa fa-plus"></i> Add Row
                                </button>
                            </div>
                            <div className="col-xs-5">
                                <br />
                                <div className="box box-info">
                                    <div className="form-horizontal">
                                        <div className="box-body">
                                            <div className="form-group" id="amount_paid_div">
                                                <label className="col-sm-4 control-label">Amount Paid</label>
                                                <div className="col-sm-8">
                                                    <input type="text" className="form-control" name="amountPaid" id="amountPaid" placeholder="Amount Paid" value={amountPaid} onChange={(e) => setAmountPaid(e.target.value)} />
                                                    
                                                </div>
                                            </div>
                                            <div className="form-group" id="amount_due_div">
                                                <label className="col-sm-4 control-label">Amount Due</label>
                                                <div className="col-sm-8">
                                                    <input type="text" className="form-control" name="amountDue" id="amountDue" placeholder="Amount Due" value={amountDue} onChange={(e) => setAmountDue(e.target.value)} />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-xs-5">
                                <div className="table-responsive">
                                    <table className="table">
                                        <tbody>
                                            <tr>
                                                <th >Item Total Amount:</th>
                                                <td><input type="text" id="itemTotal" name="itemTotal" value={itemTotal} onChange={(e) => setItemTotal(e.target.value)} />&nbsp;&nbsp;&nbsp;Rs.
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Item Total Tax :</th>
                                                <td>
                                                    <input type="text" id="itemTax" name="itemTax" value={itemTax} onChange={(e) => setItemTax(e.target.value)} />&nbsp;&nbsp;&nbsp;Rs.
                                                </td>
                                            </tr>
                                            <tr>
                                                <th >Billled Amount:</th>
                                                <td><input type="text" id="billTotal" name="billTotal" value={billTotal} onChange={(e) => setBillTotal(e.target.value)} />&nbsp;&nbsp;&nbsp;Rs.
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Round Value:</th>
                                                <td><input type="text" id="roundValue" name="roundValue" value={roundValue} onChange={(e) => setRoundValue(e.target.value)} />&nbsp;&nbsp;&nbsp;%
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Grand Total (Net Amount):</th>
                                                <td><input type="text" id="invoiceTotal" name="invoiceTotal" value={invoiceTotal} onChange={(e) => setInvoiceTotal(e.target.value)} />&nbsp;&nbsp;&nbsp;Rs.
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="row no-print">
                            <div className="col-xs-12">
                                <input type="submit" className="btn btn-success pull-right" value="Save Payment" />
                            </div>
                        </div>
                    </form>
                </section>
                <div className="clearfix"></div>
            </div>

        </div>
    );
}

export default AddInvoice;