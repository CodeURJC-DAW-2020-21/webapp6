# 6iX Shop
### Equipo de Desarrollo:
| Name        | E-mail           | GitHub  |
| :-------------: |:-------------:| :-----:|
| Sergio Martin Muñoz | s.martinmu.2018@alumnos.urjc.es | https://github.com/Fezzik23|
| Alberto Pacho Bernardos | a.pacho.2018@alumnos.urjc.es | https://github.com/AlbertoP-2018|
| Javier Espín Prieto | j.espin.2017@alumnos.urjc.es | https://github.com/jspindev|
| Celia Sanjuan Sanchez | c.sanjuansa@alumnos.urjc.es | https://github.com/csanjuansa|

### Herrmientes externas:
* Trello: https://trello.com/b/EW3wpJuS/daw

### Aspectos Principales:
* Entidades
    * **Usuario**: IdUsuario, Nombre, Contraseña, Email, ImagenPerfil, Tipo, Telefono, NTarjeta.
    * **Pedido**: IdPedido, IdDetalle, Fecha, Estado... Se relaciona con la tabla DetallesPedido a traves de un idDetalle.
    * **Producto**: IdProducto, Nombre, IdUsuario, Precio, Imagen, Descripcion, Categoria, Descuento... Se relaciona con la tabla Usuario a través de un idUsuario (Usuario el cual vende el producto)
    * **DetallePedido**: IdDetalle(id no único), IdProducto, IdUsuarioCompra, IdUsuarioVenta, NValoracion, DescValoracion, PrecioProducto, UnidadesProducto... Se relaciona con la tabla producto a traves de idProducto, con la tabla Usuario a traves de idUsuarioCompra y un idUsuarioVenta.

* Permisos
    * **Usuario Anónimo**: Puede visualizar el contenido de la web pero no puede comprar ni accedera su perfil ni a las herramientas del administrador.
    * **Usuario Registrado**: Tiene acceso a todo el contenido de la web menos a las herramientas del administrador.
    * **Administrador**: Tiene acceso a todo el contenido de la web.
    
* Imagenes
    * Se podrán subir imagenes de los productos y de la foto de perfíl de cada usuario.
   
* Graficos
    * Se permitirá visualizar el numero de ventas por mes de cada usuario.
   
* Tecnologías Complementarias
    * **Login con Google**: (El usuario podrá iniciar sesión con su cuenta de google).
    
* Algoritmos o consultas avanzadas
    * Algoritmo que permite visualizar la valoración general del producto


### FASE 1:
* **Diagrama de navegación:**

![navigationDiagram](https://user-images.githubusercontent.com/55685000/109399923-0e542800-7946-11eb-9666-52f7a160ebf1.png)

* **Capturas:**
   * Home: Página principal de la tienda, muestra un menú de navegación arriba y productos de la tienda según se va bajando.
   
   <img width="1440" alt="index" src="https://user-images.githubusercontent.com/55685000/109400066-d5688300-7946-11eb-963d-c5d33cf928a8.png">

   * Categories: Muestra las categorías de productos que se ofrecen en la tienda.
   
   <img width="1438" alt="catagori" src="https://user-images.githubusercontent.com/55685000/109400095-fcbf5000-7946-11eb-895e-7511ddc96be5.png">

   * Profile: Perfil del usuario registrado, con su nombre, foto, productos, y una barra de navegación para ver sus estadísticas, pedidos,...
   
   <img width="1440" alt="profile" src="https://user-images.githubusercontent.com/55685000/109400105-08ab1200-7947-11eb-8999-d69afe518e8b.png">

   * Single Product: El producto con sus fotos y descripción.
   
   <img width="1440" alt="singleproduct" src="https://user-images.githubusercontent.com/55685000/109400111-12cd1080-7947-11eb-8922-ca511fcb690e.png">

   * Product edition: El producto con sus fotos, descripción, y dos botones para eliminar o editar.
   
   ![productDetail](https://user-images.githubusercontent.com/55685000/109400198-9ab31a80-7947-11eb-8489-c477c9797e01.png)

   * Cart: Cesta de compra con los productos elegidos y detalles de pago de los mismos.
   
   <img width="1440" alt="cart" src="https://user-images.githubusercontent.com/55685000/109400154-5889d900-7947-11eb-8248-e35f47ee1e70.png">
   
   * Checkout: Opción de Log in o registro si es un usuario anónimo y detalles del pago del pedido.
   
   <img width="1440" alt="checkout" src="https://user-images.githubusercontent.com/55685000/109400158-5f185080-7947-11eb-936e-a00251ff289b.png">

   * Sign in: Formulario de registro para nuevos clientes.
   
   <img width="1440" alt="signin" src="https://user-images.githubusercontent.com/55685000/109400176-75261100-7947-11eb-9449-d78f79b80397.png">

   * Log in: Formulario para loguearse en la página.
   
   <img width="1440" alt="login" src="https://user-images.githubusercontent.com/55685000/109400182-7fe0a600-7947-11eb-9e87-d0aa7f25aa32.png">

   * Credit Card: Datos para el pago con tarjeta de crédito.
   
   <img width="1440" alt="card" src="https://user-images.githubusercontent.com/55685000/109400187-8707b400-7947-11eb-8ae8-1ac4656d12bd.png">

### FASE 2:

* **Instrucciones de navegación:**

Para acceder a la página web es necesario:
   - Primero clonar nuestro repositorio desde GitHub en tu ordenador https://github.com/CodeURJC-DAW-2020-21/webapp6.
   - Tener instalado un entorno de programación como Eclipse o Visual Studio Code.
   - Instalar MySQL workbench:
      Para instalarlo se necesita usar como usuario: root, y como contraseña: admin.
      A continuación, se crea un schema llamado: “db_sixshop”.
      
Una vez que tenemos ambos, entramos al entorno instalado y necesitamos descargar:
   - Maven For Java (versión 4.0.0)
   - Java Extension Pack (versión 1.11)
   - Spring Boot Extension Pack (versión 2.4.0)

Se selecciona el proyecto con click derecho y se le da a *Run as Spring Boot App*.
Por último, para abrir la página en el navegador, tendrás que poner la ruta: https://localhost:8443


* **Diagrama con las entidades de la base de datos:**


* **Diagrama de clases y templates:**





* **Participación de los miembros:**


| Nombre        | Tareas |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martin Muñoz | Tarea 1 | Tarea 2 | Tarea 3 | Tarea 4 | Tarea 5 |
| Alberto Pacho Bernardos | Tarea 1 |  Tarea 2 | Tarea 3 | Tarea 4 | Tarea 5 |
| Javier Espín Prieto | Tarea 1 | Tarea 2 | Tarea 3 | Tarea 4 | Tarea 5 |
| Celia Sanjuan Sanchez | Tarea 1 | Tarea 2 | Tarea 3 | Tarea 4 | Tarea 5 |



| Nombre        | Commits |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martin Muñoz | Commit 1 | Commit 2 | Commit 3 | Commit 4 | Commit 5 |
| Alberto Pacho Bernardos | Commit 1 |  Commit 2 | Commit 3 | Commit 4 | Commit 5 |
| Javier Espín Prieto | Commit 1 | Commit 2 | Commit 3 | Commit 4 | Commit 5 |
| Celia Sanjuan Sanchez | Commit 1 | Commit 2 | Commit 3 | Commit 4 | Commit 5 |



| Nombre        | Ficheros |  |  |  |  |
| :-------------: |:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Sergio Martin Muñoz | Fichero 1 | Fichero 2 | Fichero 3 | Fichero 4 | Fichero 5 |
| Alberto Pacho Bernardos | Fichero 1 |  Fichero 2 | Fichero 3 | Fichero 4 | Fichero 5 |
| Javier Espín Prieto | Fichero 1 | Fichero 2 | Fichero 3 | Fichero 4 | Fichero 5 |
| Celia Sanjuan Sanchez | Fichero 1 | Fichero 2 | Fichero 3 | Fichero 4 | Fichero 5 |
