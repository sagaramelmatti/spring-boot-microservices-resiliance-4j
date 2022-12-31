import http from "../http-common";

const getAll = () => {
    return http.get("/master/category");
  };
  const get = id => {
    return http.get(`/master/category/${id}`);
  };
  const create = data => {
    return http.post("/master/category", data);
  };
  const update = (id, data) => {
    return http.put(`/master/category/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/master/category/${id}`);
  };

const CategoryDataService =  {
      getAll,
      get,
      create,
      update,
      remove,
};

export default CategoryDataService;