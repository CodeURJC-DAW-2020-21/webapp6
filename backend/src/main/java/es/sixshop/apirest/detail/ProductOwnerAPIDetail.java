package es.sixshop.apirest.detail;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface ProductOwnerAPIDetail
	extends Product.Basic, Product.Users, 
	User.Basic, User.Owner {
}
