package es.sixshop.apirest.detail;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface UserAPIDetail 
	extends User.Basic, User.Products, 
		Product.Basic{

}
