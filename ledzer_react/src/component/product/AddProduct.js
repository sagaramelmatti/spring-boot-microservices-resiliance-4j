import React, { useState, useEffect } from 'react';
import Select from 'react-select';
import axios from 'axios';
import ProductDataService from '../../services/ProductDataService';

function AddProduct(props) {

    const [items, setItems] = React.useState([]);

    React.useEffect(() => {
      async function getCharacters() {
        const response = await fetch("http://localhost:9191/api/master/category/");
        const body = await response.json();
        setItems(body.map(item => {
            return { value: item.id, label: item.name };
          }));
      }
      getCharacters();
    }, []);


    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [hsnCode, setHsnCode] = useState("");
    const [categoryId, setCategoryId] = useState("");
    const [rate, setRate] = useState("");
    const [gstPer, setGstPer] = useState("");
    const [model, setModel] = useState("");
    const [serialNo, setSerialNo] = useState("");

    useEffect(() => {
        setId(localStorage.getItem('iD'));
        setName(localStorage.getItem('name'));
        setHsnCode(localStorage.getItem('hsnCode'));
        setCategoryId(localStorage.getItem('categoryId'));
        setRate(localStorage.getItem('rate'));
        setGstPer(localStorage.getItem('gstPer'));
        setModel(localStorage.getItem('model'));
        setSerialNo(localStorage.getItem('serialNo'));
        
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        alert(`The name you entered was: ${name}`)
        var data = {
            name : name,
            hsnCode : hsnCode,
            categoryId : categoryId,
            rate : rate,
            gstPer : gstPer,
            model : model,
            serialNo : serialNo,
        };

        console.log(data);

        ProductDataService.create(data)
          .then(response => {
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

    }


    const saveProduct = () => {
        
        /*
        ProductDataService.create(data)
          .then(response => {
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
        */
    };

    return (
        <>
            <div className="content-wrapper">
                <section className="content-header">
                    <h1>Add New Product</h1>
                </section>
                <section className="content">
                    <div className="row">
                        <div className="col-xs-12">
                            <div className="box">
                                <div className="box-header">
                                    <h3 className="box-title"> Product Master</h3>
                                </div>
                                <div className="box-body">
                                    <form method ="post" className='form' onSubmit={handleSubmit}>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <div className="form-group required">
                                                <label className="control-label">HSN / SAC Code</label>
                                                <input type="text" className="form-control" name="hsnCode" value={hsnCode} onChange={(e) => setHsnCode(e.target.value)} />
                                            </div>
                                        </div>
                                        <div className="col-md-6">
                                            <div className="form-group required">
                                                <label className="control-label">Product Name</label>
                                                <input type="text" className="form-control" name="name" value={name} onChange={(e) => setName(e.target.value)} />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <div className="form-group required">
                                                <label className="control-label">CategoryId</label>
                                                    <select className='form-control select2' value={categoryId} onChange={(e) => setCategoryId(e.target.value)}>
                                                        {items.map(o => (
                                                            <option key={o.value} value={o.value}>{o.label}</option>
                                                        ))}
                                                    </select>
                                            </div>
                                        </div>
                                        <div className="col-md-6">
                                            <div className="form-group required">
                                                <label className="control-label">Rate</label>
                                                <input type="text" className="form-control" name="rate" value={rate} onChange={(e) => setRate(e.target.value)} />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <div className="form-group">
                                                <label>Model</label>
                                                <input type="text" className="form-control" name="model" value={model} onChange={(e) => setModel(e.target.value)} />
                                            </div>
                                        </div>
                                        <div className="col-md-6">
                                            <div className="form-group required">
                                                <label>Serial No.</label>
                                                <input type="text" className="form-control" name="serialNo" value={serialNo} onChange={(e) => setSerialNo(e.target.value)} />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <div className="form-group">
                                                <label>GstPer</label>
                                                <input type="text" className="form-control" name="gstPer" value={gstPer} onChange={(e) => setGstPer(e.target.value)} />
                                            </div>
                                        </div>
                                       
                                    </div>
                                    <div className="row">
                                        <div className="col-md-3">
                                        </div>
                                        <div className="col-md-3">
                                            <button type="submit" className="btn btn-success btn-block btn-flat r-btn">Save</button>
                                        </div>
                                        <div className="col-md-6">
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </>
    );
}

export default AddProduct;