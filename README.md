# 👽 6iX Shop

## Index 🛸
- [PHASE 0](#phase-0)
   + [Development team](#development-team)
   + [Organization tools](#organization-tools)
   + [Introduction](i#ntroduction)
   + [Principal aspects](#principal-aspects)
   + [Users and their permissions](#users-and-their-permissions)
   + [Images](#images)
   + [Graphics](#graphics)
   + [Complementary technologies](#complementary-technologies)
   + [Algorithm or advanced queries](#algorithm-or-advanced-queries)

- [PHASE 1](#phase-1)
   + [Navigation diagram](#navigation-diagram)
   + [Screenshots](#screenshots)

- [PHASE 2](#phase-2)
   + [Instructions](#instructions)
   + [Database entities diagram](#database-entities-diagram)
   + [Templates and classes diagram](#templates-and-classes-diagram)
   + [Member participation](#member-participation)

## PHASE 0
### Development team🧑🏻‍💻🧑🏽‍💻🧑🏻‍💻👩🏽‍💻:
| Name        | E-mail           | GitHub  |
| :-------------: |:-------------:| :-----:|
| Sergio Martin Muñoz | s.martinmu.2018@alumnos.urjc.es | https://github.com/Fezzik23|
| Alberto Pacho Bernardos | a.pacho.2018@alumnos.urjc.es | https://github.com/AlbertoP-2018|
| Javier Espín Prieto | j.espin.2017@alumnos.urjc.es | https://github.com/jspindev|
| Celia Sanjuan Sanchez | c.sanjuansa@alumnos.urjc.es | https://github.com/csanjuansa|

### Organization tools🛠:
* Trello: https://trello.com/b/EW3wpJuS/daw

### Introduction:
👽 6ixShop is an online shopping site where you can buy and sell prints or posters of your favourite movies, TV series, music, comics,... or even custom! By creating an account, you can upload your prints to sell and in your profile you will see all the prints you’ve bought and all the ones you’ve sold. Welcome to 👽 6ixShop!

### Principal aspects:
* Entities:
    * **User**: idUser, rol, nickName, password, email, phoneNumber, cardNumber, image.
    * **Product**: idProduct, idUser, productName, description, category, price, image, rating.
    * **Order**: idOrder, idOrderDetail, date, status.
    * **OrderDetails**: idOrderDetail(not unique), idProduct, idBuyerUser, idSellerUser, rating, descRating, productPrice, phoneNumber.

### Users and their permissions👤:
   - **Anonymous user**: They will be able to visualize all the website content, however, they won’t be able to buy or sell neither to have access to the profile page or to the admin tools.
   - **Registered user**: They will have access to all the website content except to the admin tools.
   - **Admin user**: They will have access to all the website content.
    
### Images🎆:
   - Product and profile images can be uploaded by users.
   
### Graphics📈:
   - It will be possible to visualize the number of sales per month for each user.
   
### Complementary technologies:
   - **Login with Google**: The user will be able to log in with their google account.
    
### Algorithm or advanced queries:
   - Algorithm that allows to visualize the general rating of a product.

## PHASE 1:
### **Navigation diagram:**

![navigationDiagram](https://user-images.githubusercontent.com/55685000/109399923-0e542800-7946-11eb-9666-52f7a160ebf1.png)

### **Screenshots:**
   * **Home**: Homepage of the website which shows a navigation menu and products of the shopping site as you slide down.
   
   <img width="1440" alt="index" src="https://user-images.githubusercontent.com/55685000/109400066-d5688300-7946-11eb-963d-c5d33cf928a8.png">

   * **Categories**: It shows all the product categories we offer on the website.
   
   <img width="1438" alt="catagori" src="https://user-images.githubusercontent.com/55685000/109400095-fcbf5000-7946-11eb-895e-7511ddc96be5.png">

   * **Profile**: Registered user profile with their name, picture, products, and a navigation bar to view their statistics, orders,...
   
   <img width="1440" alt="profile" src="https://user-images.githubusercontent.com/55685000/109400105-08ab1200-7947-11eb-8999-d69afe518e8b.png">

   * **Single Product**: The product with its pictures and description.
   
   <img width="1440" alt="singleproduct" src="https://user-images.githubusercontent.com/55685000/109400111-12cd1080-7947-11eb-8922-ca511fcb690e.png">

   * **Product editor**: The product with its pictures and description, and two buttons for editing and removing.   
   ![productDetail](https://user-images.githubusercontent.com/55685000/109400198-9ab31a80-7947-11eb-8489-c477c9797e01.png)

   * **Cart**: Shopping cart with the chosen products and their payment details.
   
   <img width="1440" alt="cart" src="https://user-images.githubusercontent.com/55685000/109400154-5889d900-7947-11eb-8248-e35f47ee1e70.png">
   
   * **Checkout**: Login or register options and order payment details.
   
   <img width="1440" alt="checkout" src="https://user-images.githubusercontent.com/55685000/109400158-5f185080-7947-11eb-936e-a00251ff289b.png">

   * **Sign in**: Register form for new users.
   
   <img width="1440" alt="signin" src="https://user-images.githubusercontent.com/55685000/109400176-75261100-7947-11eb-9449-d78f79b80397.png">

   * **Log in**: Page login form.
   
   <img width="1440" alt="login" src="https://user-images.githubusercontent.com/55685000/109400182-7fe0a600-7947-11eb-9e87-d0aa7f25aa32.png">

   * **Credit Card**: Data for the credit card payment.
   
   <img width="1440" alt="card" src="https://user-images.githubusercontent.com/55685000/109400187-8707b400-7947-11eb-8ae8-1ac4656d12bd.png">


## PHASE 2:

### **Instructions💻:**

To access the shopping site you its needed:
   - First of all, clone the repository on GitHub in your computer https://github.com/CodeURJC-DAW-2020-21/webapp6.
   - Install any programming environment for web server development tools, for example, Eclipse or Visual Studio Code.
   - Install MySQL workbench:
      For the installation its needed the user: *root*, and the password: *admin*.
      To be continued, create a schema called: *“db_sixshop”*.
      
Once we have both, enter to the environment and proceed to download:
   - Maven For Java (version 4.0.0)
   - Java Extension Pack (version 1.11)
   - Spring Boot Extension Pack (version 2.4.0)

Select the proyect with right click and click on *Run as Spring Boot App*.
Finally, to open the website in your browser you will have to write this path: https://localhost:8443


### **Database entities diagram:**


### **Templates and classes diagram:**





### **Member participation🙌🏻:**


| Name        | Tasks |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martin Muñoz | Tarea 1 | Tarea 2 | Tarea 3 | Tarea 4 | Tarea 5 |
| Alberto Pacho Bernardos | | | | | |
| Javier Espín Prieto | | | | | |
| Celia Sanjuan Sanchez | | | | | |



| Name        | Commits |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martin Muñoz | Commit 1 | Commit 2 | Commit 3 | Commit 4 | Commit 5 |
| Alberto Pacho Bernardos | | | | | |
| Javier Espín Prieto | | | | | |
| Celia Sanjuan Sanchez | | | | | |



| Name        | Files |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martin Muñoz | Fichero 1 | Fichero 2 | Fichero 3 | Fichero 4 | Fichero 5 |
| Alberto Pacho Bernardos | | | | | |
| Javier Espín Prieto | | | | | |
| Celia Sanjuan Sanchez | | | | | |



## PHASE 3

## PHASE 4
