<h2>VIVE LIBRE - PRUEBA TÉCNICA</h2>

<p>Aplicación springboot con docker-compose para levantar la imagen que creará automáticamente una bbdd mysql con tabla de usuarios, para poder loggearte y tener acceso a los demás endpoints.</p>

<p>Consta de autentificación jwt, por lo que hay que registrarse, loggearse y utilizar el token generado para poder acceder en postman a los endpoints de los ejercicios.</p>

<p>Podeís importar el <a href="https://github.com/sergiom98/ViveLibre/blob/main/ViveLibre%20-%20PruebaT%C3%A9cnica.postman_collection.json">json de postman</a> del repositorio para que tengáis más agilidad a la hora de las pruebas</p>
<p>Los únicos endpoitns que están abierto por defecto (el puerto corre en 8081) son:</p> 
<ul>
  <li><b>/token</b> Para poder llamar a vuestra imagen docker y devolver el token con mensaje satisfacotrio y timestamp de la fecha actual</li>
  <li><b>/auth/registro</b> Para registrarse en la aplidación y así poder hacer login</li>
  <li><b>c/login</b> Para loggearte en la aplicación y así obtener el token necesario para poder acceer a los demás endpoints</li>
</ul>
