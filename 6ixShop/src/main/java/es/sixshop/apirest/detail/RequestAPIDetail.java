package es.sixshop.apirest.detail;

import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.model.Product;

public interface RequestAPIDetail 
	extends Request.Basic, Request.RequestDetails, Request.Users,
		RequestDetail.Basic,
		User.Basic,
		Product.Basic{
}
