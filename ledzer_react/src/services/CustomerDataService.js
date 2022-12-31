import http from "../http-common";

const getAll = () => {
    return http.get("/master/customers");
  };
  const get = id => {
    return http.get(`/master/customers/${id}`);
  };
  const create = data => {
    return http.post("/master/customers", data);
  };
  const update = (id, data) => {
    return http.put(`/master/customers/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/master/customers/${id}`);
  };

const CustomerDataService =  {
      getAll,
      get,
      create,
      update,
      remove,
};

export default CustomerDataService;