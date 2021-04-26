package es.sixshop.apirest.detail;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface ProductAPIDetail 
	extends Product.Basic, Product.Users, 
		User.Basic {
}
