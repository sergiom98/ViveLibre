<h2>VIVE LIBRE - PRUEBA TÉCNICA</h2>

<p>Aplicación springboot con docker-compose para levantar la imagen que creará automáticamente una bbdd MySQL con tabla de usuarios, para poder loggearte y tener acceso a los demás endpoints.</p>

<p>Consta de autenticación jwt, por lo que hay que registrarse, loguearse y utilizar el token generado para poder acceder en postman a los endpoints de los ejercicios.</p>

<p><b><a href="https://github.com/sergiom98/ViveLibre/blob/main/ViveLibre%20-%20openapi3_0.yaml">Documentación Swagger</a></b></p> 
<p>Podéis importar el <b><a href="https://github.com/sergiom98/ViveLibre/blob/main/ViveLibre%20-%20PruebaT%C3%A9cnica.postman_collection.json">JSON de Postman</a></b> del repositorio para que tengáis más agilidad a la hora de las pruebas</p>
<p>Los únicos endpoints que están abiertos por defecto (el puerto corre en 8081) son:</p> 
<ul>
  <li><b>/token</b> Para poder llamar a vuestra imagen docker y devolver el token con mensaje satisfactorio y timestamp de la fecha actual</li>
  <li><b>/auth/registro</b> Para registrarse en la aplicación y así poder hacer login</li>
  <li><b>/auth/login</b> Para loguearte en la aplicación y así obtener el token necesario para poder acceder a los demás endpoints</li>
</ul>
