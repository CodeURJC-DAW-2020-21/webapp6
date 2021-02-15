# 6iX Shop
### Equipo de Desarrollo :
| Name        | E-mail           | GitHub  |
| :-------------: |:-------------:| :-----:|
| Sergio Martin Muñoz | s.martinmu.2018@alumnos.urjc.es | https://github.com/Fezzik23|
| Alberto Pacho Bernardos | a.pacho.2018@alumnos.urjc.es | https://github.com/AlbertoP-2018|
| Javier Espín Prieto | j.espin.2017@alumnos.urjc.es | https://github.com/jspindev|
| Celia Sanjuan Sanchez | c.sanjuansa@alumnos.urjc.es | https://github.com/csanjuansa|

### Herrmientes externas :
* Trello : https://trello.com/b/EW3wpJuS/daw

### Aspectos Principales :
* Entidades
    * **Usuario**: idUsuario...
    * **Producto**: idProducto... Se relaciona con la tabla Usuario a través de un idUsuario (Usuario el cual vende el producto)
    * **Transacciones**: idTransaccion... Se relaciona con la tabla ventas a traves de un idVenta.
    * **cestaCompra**: idCesta... Se relaciona con la tabla Usuario a través del idUsuario y con la tabla Producto a través de idProducto.
    * **Ventas**: idVenta (id no único)... se relaciona con la tabla producto a traves de idProducto, con la tabla Usuario a traves de idUsuarioCompra y un idUsuarioVenta (Esta tabla se usa para relacionar mas tablas).

* Permisos
    * **Usuario Anónimo**: Puede visualizar el contenido de la web pero no puede comprar ni accedera su perfil ni a las herramientas del administrador.
    * **Usuario Registrado**: Tiene acceso a todo el contenido de la web menos a las herramientas del administrador.
    * **Administrador**: Tiene acceso a todo el contenido de la web.
    
* Imagenes
   Se podrán subir imagenes de los productos y de la foto de perfíl de cada usuario.
   
* Graficos
   Se permitirá visualizar el numero de ventas por mes de cada usuario y un gráfico que contemple el numero de ventas de cada categoría.
   
* Tecnologías Complementarias
    * **Envio de correos a los usuarios**: (Cuando se realice una compra o cuando se olvide una contraseña).
    * **Login con Google**: (El usuario podrá iniciar sesión con su cuenta de google).
    
* Algoritmos o consultas avanzadas
   Algoritmo que permite visualizar la valoración general del producto
