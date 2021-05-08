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
   + [Navigation diagram updated](#navigation-diagram-updated)
   + [Database entities diagram](#database-entities-diagram)
   + [Templates and classes diagram](#templates-and-classes-diagram)
   + [Member participation Phase 2](#member-participation-phase-2)

- [PHASE 3](#phase-3)
   + [API REST Documentation](#api-rest-documentation)
   + [Templates and classes diagram updated](#templates-and-classes-diagram-updated)
   + [Instructions for executing the dockerized app](#instructions-for-executing-the-dockerized-app)
   + [Docker image documentation](#docker-image-documentation)
   + [Member participation Phase 3](#member-participation-phase-3)

- [PHASE 4](#phase-4)
   + [Installation process and instructions](#installation-process-and-instructions)
   + [Templates and classes diagram final version](#templates-and-classes-diagram-final-version)
   + [Member participation Phase 4](#member-participation-phase-4)

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
    * **User**: idUser, rol, nickName, password, email, phoneNumber, image.
    * **Product**: idProduct, idUser, productName, description, category, price, image, rating.
    * **Request**: idRequest, date, status, idBuyerUser.
    * **RequestDetail**: idRequestDetail, idRequest, idProduct, rating, descRating, productPrice.

### Users and their permissions👤:
   - **Anonymous user**: They will be able to visualize all the website content, however, they won’t be able to buy or sell neither to have access to the profile page or to the admin tools.
   - **Registered user**: They will have access to all the website content except to the admin tools.
   - **Admin user**: They will have access to all the website content.
    
### Images🎆:
   - Product and profile images can be uploaded by users.
   
### Graphics📈:
   - It will be possible to visualize the number of sales per month for each user.
   
### Complementary technologies:
   - **Send email if a product is uploaded**: The user will receive an email if they have uploaded a product.
    
### Algorithm or advanced queries:
   - Algorithm that allows to visualize the general rating of a product.

## PHASE 1:
### **Navigation diagram:**
Updated in Phase 2.

### **Screenshots:**
   * **Home**: Homepage of the website which shows a navigation menu and products of the shopping site as you slide down.
   
   <img width="1258" alt="index" src="https://user-images.githubusercontent.com/55685000/113615683-70463280-9654-11eb-864c-45f4e6890e14.png">

   * **Categories**: It shows all the product categories we offer on the website.
   
   <img width="1187" alt="category" src="https://user-images.githubusercontent.com/55685000/113615741-881db680-9654-11eb-8b58-8edc9f67d2bd.png">

   * **Profile**: Registered user profile with their name, picture, products, and a navigation bar to view their statistics, orders,...
   
   <img width="1440" alt="profile" src="https://user-images.githubusercontent.com/55685000/113614654-14c77500-9653-11eb-8b50-2b57d8ede0d1.png">

   * **Single Product**: The product with its pictures and description.
   
   <img width="1440" alt="singleproduct" src="https://user-images.githubusercontent.com/55685000/109400111-12cd1080-7947-11eb-8922-ca511fcb690e.png">

   * **Product editor**: The product with its pictures and description, and two buttons for editing and removing. 
   
   <img width="1187" alt="productDetail" src="https://user-images.githubusercontent.com/55685000/113614816-49d3c780-9653-11eb-812b-81c458719740.png">  
   <img width="1440" alt="editProduct" src="https://user-images.githubusercontent.com/55685000/113614800-404a5f80-9653-11eb-84d8-98f18e89fc26.png">

   * **Cart**: Shopping cart with the chosen products and their payment details.
   
   <img width="1187" alt="cart" src="https://user-images.githubusercontent.com/55685000/113615789-98ce2c80-9654-11eb-9127-8af943c035cf.png">

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

### **Navigation diagram updated:**
![navigationDiagram](https://user-images.githubusercontent.com/55685000/113612324-f8760900-964f-11eb-804b-c3ccd0024235.png)

### **Database entities diagram:**
![DiagramaBD](https://user-images.githubusercontent.com/55685000/113612452-29563e00-9650-11eb-89a7-dc82e4b5c224.jpeg)

### **Templates and classes diagram:**
![diagramaClasesTemplates](https://user-images.githubusercontent.com/55685000/113672670-55a5a500-96b8-11eb-86a4-4fc27098175e.png)

### **Member participation Phase 2🙌🏻:**


| Name        | Tasks |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | Rating | Cart functionality | Delete product | Product rating | Edit product |
| Alberto Pacho Bernardos | Load more | Rating algorithm | Send email | Check paid orders | Graphic stadistics |
| Javier Espín Prieto | Login and Sign up | Spring security | Graphic stadistics | Users control | Error page |
| Celia Sanjuán Sánchez | Edit product | Load products in cart | Create order | Diagrams | ReadMe |



| Name        | Commits |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/e4dd8414bdc9375dc4ab289f8860333bde4aa90e) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/888b145256990e558f6cfe8ccf202e0701550725) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/1fd499da40b9990b9238bd3814b9b3001e8d8dd3) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/a7cdaa78e048b347534da81bc5eaee7822d3fda7) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7f992e853d8e084b00f9a9aebd5a7e3860496074) |
| Alberto Pacho Bernardos | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7cd093eb5fb60d2ffeebafcb66599288b640a093) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7cd093eb5fb60d2ffeebafcb66599288b640a093) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/3bc4159059a21160602e3bb3c98bb8e2317d0e81) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/4a1b49ca50aeec00bf1e20aa9a3a529e9d820e02) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/dd6de434f87a15aa9cc6d9e85793fd5f1d2a8dcd) |
| Javier Espín Prieto | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/a8d4f18bbe0da63d0959e8e8a52d7836ae4b0501) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/13f5ed40927199721b8e6bb27f75736e5f088583) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/dd6de434f87a15aa9cc6d9e85793fd5f1d2a8dcd) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/a8d4f18bbe0da63d0959e8e8a52d7836ae4b0501) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/a8d4f18bbe0da63d0959e8e8a52d7836ae4b0501) |
| Celia Sanjuán Sánchez | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7f992e853d8e084b00f9a9aebd5a7e3860496074) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7f992e853d8e084b00f9a9aebd5a7e3860496074) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7f992e853d8e084b00f9a9aebd5a7e3860496074) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/6bd9e048a8ac8e596a731dcef2d12fd23fc41f3f) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/26f557cbe9611a5d633648d9cd9a7014037c45b9) |



| Name        | Files |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | [ProductController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/controller/ProductController.java) | [Product.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/model/Product.java) | [CartController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/controller/CartController.java) | [User.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/model/User.java) | [single-product.html](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/resources/templates/single-product.html) |
| Alberto Pacho Bernardos | [es.sixshop.controller.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/6ixShop/src/main/java/es/sixshop/controller) | [es.sixshop.model.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/6ixShop/src/main/java/es/sixshop/model) | [es.sixshop.repository.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/6ixShop/src/main/java/es/sixshop/repository) | [es.sixshop.security.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/6ixShop/src/main/java/es/sixshop/security) | [es.sixshop.service.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/6ixShop/src/main/java/es/sixshop/service) |
| Javier Espín Prieto | [ProfileController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/6ixShop/src/main/java/es/sixshop/controller/ProfileController.java) | [SecurityConfiguration.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/6ixShop/src/main/java/es/sixshop/security/SecurityConfiguration.java) | [CSRFHandlerConfiguration.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/6ixShop/src/main/java/es/sixshop/security/CSRFHandlerConfiguration.java) | [UserController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/6ixShop/src/main/java/es/sixshop/controller/UserController.java) | [UserService.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/6ixShop/src/main/java/es/sixshop/service/UserService.java) |
| Celia Sanjuán Sánchez | [ProductController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/controller/ProductController.java) | [Product.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/model/Product.java) | [Request.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/model/Request.java) | [CartController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/orderDatabase/6ixShop/src/main/java/es/sixshop/controller/CartController.java) | [ReadMe](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/README.md) |



## PHASE 3

### **API REST Documentation:**
   + [OpenAPI](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/api-docs/api-docs%20.yaml) (apidocs.yaml)
   + [Documentation](https://rawcdn.githack.com/CodeURJC-DAW-2020-21/webapp6/dev/api-docs/api-docs.html) (api-docs.html)
### **Templates and classes diagram updated:**
![diagramaClasesTemplatesUpdated](https://user-images.githubusercontent.com/55685000/115127076-f149e600-9fd3-11eb-895c-b783b405bdc2.png)

### **Instructions for executing the dockerized app:**
   1. First, it's needed the Docker installation and a docker account.
   2. Once it's installed, proceed to open the *shell*.
   3. Position yourself in the *Docker folder* from the proyect.
   4. If you're using Mac or Windows you have to write: *docker-compose up --build -d*
   5. Then, write in your explorer: *https://localhost:8443/* and you'll see the website.

### **Docker image documentation:**
   1. First, it's needed the Docker installation and a docker account.
   2. Once it's installed, proceed to open the *shell*.
   3. Clone the repository from GitHub using this command: *git clone https://github.com/CodeURJC-DAW-2020-21/webapp6*
   4. Position yourself in the *Docker folder* from the proyect.
   5. For creating the image, you have to write in the shell: *bash start.sh*
   6. The shell will ask you for the docker password.
   7. Finally, check the docker image in your docker account.

### **Member participation Phase 3🙌🏻:**


| Name        | Tasks |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | Dockerfile | Docker-compose | Script de bash | Docker instructions | Diagram |
| Alberto Pacho Bernardos | API REST | API REST Controllers | API REST Details | Postman queries |  |
| Javier Espín Prieto | Security Configuration | Security jwt | OpenAPI | Documentation |  |
| Celia Sanjuán Sánchez | Dockerfile | Docker-compose | Script de bash | Diagram & Docker instructions | ReadMe |



| Name        | Commits |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/08568bdff2aa092a6db9f5f7c2da860939492b58) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/08568bdff2aa092a6db9f5f7c2da860939492b58) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/08568bdff2aa092a6db9f5f7c2da860939492b58) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/ee6386a8bf68a13a8ea924a17c1a1313779da971) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/95840f844617adafee5f99d7a90b377d8b352e84) |
| Alberto Pacho Bernardos | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/2f2ca9eb5e03d2a98184222c6584ddb6a9da417a) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/e6c343b42308ea6bf5b347f917cf49300400c98e) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/1c87a6d70129835392c4ff1e4629194b7a840556) |  [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/c25bc7097294438e07e8baf6ad783218b58c29b0) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/88f1477bf6d5594369093c3be92835c121eabd1a) |
| Javier Espín Prieto | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/478eb67c59304f4822be8a692b31864266e4ca60) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/478eb67c59304f4822be8a692b31864266e4ca60) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/478eb67c59304f4822be8a692b31864266e4ca60) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/380e2df41f4248b963c2d3fb32976b21fdfe885d) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/9a8fccd8db507ca5bc1d97b0c6062014054f6b27) |
| Celia Sanjuán Sánchez | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/08568bdff2aa092a6db9f5f7c2da860939492b58) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/08568bdff2aa092a6db9f5f7c2da860939492b58) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/08568bdff2aa092a6db9f5f7c2da860939492b58) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/95840f844617adafee5f99d7a90b377d8b352e84) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/5d09435e031c8cd4065e1337e223e57532c91791) |



| Name        | Files |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | [Dockerfile](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/Docker/Dockerfile) | [Docker-compose.yml](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/Docker/docker-compose.yml) | [create_image.sh](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/Docker/start.sh) | [pom.xml](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/pom.xml) | [ReadMe](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/README.md) |
| Alberto Pacho Bernardos | [es.sixshop.apirest.controller.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/apiRest/6ixShop/src/main/java/es/sixshop/apirest/controller) | [es.sixshop.apirest.detail.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/apiRest/6ixShop/src/main/java/es/sixshop/apirest/detail) | [es.sixshop.security.jwt.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/apiRestSecurity/6ixShop/src/main/java/es/sixshop/security/jwt) | [es.sixshop.model.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/apiRestSecurity/6ixShop/src/main/java/es/sixshop/model) | [es.sixshop.service.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/6ixShop/src/main/java/es/sixshop/model) |
| Javier Espín Prieto | [ApiRestSecurityConfiguration.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/APIsecurity/6ixShop/src/main/java/es/sixshop/apirest/security/ApiRestSecurityConfiguration.java) | [CartRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/APIsecurity/6ixShop/src/main/java/es/sixshop/apirest/controller/CartRestController.java) | [ProductRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/APIsecurity/6ixShop/src/main/java/es/sixshop/apirest/controller/ProductRestController.java) | [ProfileRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/APIsecurity/6ixShop/src/main/java/es/sixshop/apirest/controller/ProfileRestController.java) | [es.sixshop.security.jwt.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/APIsecurity/6ixShop/src/main/java/es/sixshop/security/jwt) |
| Celia Sanjuán Sánchez | [Dockerfile](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/Docker/Dockerfile) | [Docker-compose.yml](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/Docker/docker-compose.yml) | [create_image.sh](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/Docker/start.sh) | [pom.xml](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/Docker/6ixShop/pom.xml) | [ReadMe](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/README.md) |

## PHASE 4

### **Installation process and instructions:**
   1. First of all, you need to install [Nodejs](https://nodejs.org/es/) and Angular with: *npm install -g @angular/cli* in the *shell*.
   2. Once it's installed, clone the repository from GitHub using this command: *git clone https://github.com/CodeURJC-DAW-2020-21/webapp6*
   3. Then, position yourself in the *frontend folder* from the proyect.
   4. Write: *npm install*
   5. And, to laucnch the app, write: *ng serve*
   6. Finally, write in your explorer: *https://localhost:4200/* and you'll see the website.

### **Templates and classes diagram final version:**
![lasses-templatesDiagram](https://user-images.githubusercontent.com/55685000/117555056-2c33bc80-b05c-11eb-9020-d79990536e1e.png)


### **Member participation Phase 3🙌🏻:**

| Name        | Tasks |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | Single-product | Script de bash | Cart | Diagram&Instructions | ReadMe |
| Alberto Pacho Bernardos | Routing | Index | Category | Profile | Cart&Pay |
| Javier Espín Prieto | Login | Signin | Edit Product | Remove Product | Security |
| Celia Sanjuán Sánchez | Single-product | Script de bash | Cart | Diagram&Instructions | ReadMe |



| Name        | Commits |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/5ef3b277e9b2494650de662894ae2100ac24e959) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/5ef3b277e9b2494650de662894ae2100ac24e959) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/63c6393c0843159c20088cbf81b957da32efaf5f) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7caf2a11090f1f72fcc3763ca4c3a6efba5ad705) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/0a4d2df0e1b30328ed5acd4102c3fbd6bcdcb550) |
| Alberto Pacho Bernardos | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/2064324c8a3c0815a96800bea1c704e973ecf6c8) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/008c3e43fd201e95c11684f91b6641362707f42a) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/a38777701719673a54f6229e0c9e5f8ac9f10e14) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/cc394f82dfc5f2d17239ad79ce065a6ae8cc60c1) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/2d6b11d610dc612e9c99b7bc372f32e3e2a7e6e8) |
| Javier Espín Prieto | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/2034819d1f0759b960e08a00abb0299c973ed661) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/ed62c80143edb53f0a223ef9b30401b0c0691d8c) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/ecc781c31db4a03a1124444d7179f155822c43a9) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/61dc6aaeb1720d5df39c701f71472b6fea6908dd) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/893502edd54c2f50155242cf10d4ea9b84fb5e21) |
| Celia Sanjuán Sánchez | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/63c6393c0843159c20088cbf81b957da32efaf5f) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/7caf2a11090f1f72fcc3763ca4c3a6efba5ad705) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/5ef3b277e9b2494650de662894ae2100ac24e959) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/5ef3b277e9b2494650de662894ae2100ac24e959) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp6/commit/0a4d2df0e1b30328ed5acd4102c3fbd6bcdcb550) |



| Name        | Files |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martín Muñoz | [frontend/.../single-product.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/products/single-product) | [create_image.sh](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/backend/Docker/create_image.sh) | [Dockerfile](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/backend/Docker/Dockerfile) | [ReadMe](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/README.md) | [frontend/.../products-all.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/products/products-all) |
| Alberto Pacho Bernardos | [frontend/.../components.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components) | [frontend/.../models.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/models) | [frontend/.../services.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/services) | [frontend/.../environments.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/environments) | |
| Javier Espín Prieto | [frontend/.../sign-in.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/user/sign-in) | [frontend/.../login.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/user/login) | [frontend/.../edit-product.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/products/edit-product) | [frontend/.../single-product.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/products/single-product) | |
| Celia Sanjuán Sánchez | [frontend/.../single-product.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/products/single-product) | [create_image.sh](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/backend/Docker/create_image.sh) | [Dockerfile](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/backend/Docker/Dockerfile) | [ReadMe](https://github.com/CodeURJC-DAW-2020-21/webapp6/blob/dev/README.md) | [frontend/.../products-all.*](https://github.com/CodeURJC-DAW-2020-21/webapp6/tree/dev/frontend/src/app/components/products/products-all) |
