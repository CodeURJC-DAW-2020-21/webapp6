package es.sixshop.apirest.detail;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface UserOwnerAPIDetail
	extends User.Basic, User.Products, User.Owner, 
	Product.Basic{

}
