package es.sixshop.apirest.detail;

import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface RequestDetailAPIDetail 
	extends RequestDetail.Basic, RequestDetail.Products, RequestDetail.Requests,
		Request.Basic, Request.Users,
		Product.Basic,
		User.Basic{
}
