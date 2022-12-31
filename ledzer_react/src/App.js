import { BrowserRouter as Router,  Route, Routes} from 'react-router-dom';
import AddCustomer from "./component/customer/AddCustomer";
import CustomerList from "./component/customer/CustomerList";
import CategoryList from './component/category/CategoryList';
import AddCategory from "./component/category/AddCategory";
import Topmenu from "./component/Topmenu";
import AddProduct from './component/product/AddProduct';
import ProductList from './component/product/ProductList';
import InvoiceList from './component/invoice/InvoiceList';
import AddInvoice from './component/invoice/AddInvoice';
import ViewInvoice from './component/invoice/ViewInvoice';
import EditCustomer from './component/customer/EditCustomer';

function App() {
  return (
    <>
      <body className="hold-transition skin-blue layout-top-nav">
        <div className="wrapper">

          <Router>
            <Topmenu></Topmenu>
              <Routes>
                <Route path='/' exact element={ <CustomerList/>  } />
                <Route path='/addCustomer' exact element={ <AddCustomer/>  } />
                <Route path='/customers' element={ <CustomerList/> } />
                <Route path='/customers/:id' element={ <EditCustomer/> } />
                <Route path='/addProduct' exact element={ <AddProduct/>  } />
                <Route path='/products' element={ <ProductList/> } />
                <Route path='/addInvoice' exact element={ <AddInvoice/>  } />
                <Route path='/invoices' element={ <InvoiceList/> } />
                <Route path="/invoices/:id" element={<ViewInvoice/>} />
                <Route path='/category' element={ <CategoryList/> } />
                <Route path='/addCategory' exact element={ <AddCategory/>  } />
              </Routes>
          </Router>
        </div>
      </body>
    </>
  );
}

export default App;
