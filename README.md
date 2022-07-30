<h1>Guia básica para instalar y usar el sistema.</h1>

<p>1. Descargar el preoyecto en su repositorio local.</p>
<p>2. Ejecutar el script para generar la base de datos en MySQL.</p>
[ubicacion del Script dentro del proyecto]https://github.com/laura931/ProyectoFinal/issues/1#issue-1322819683
<p>3. Ejecutar el proyecto, indicando la clase "app" como la clase principal.</p>


<h2>Menú de opciones del sistema</h2>
<p>Este proyecto está pensado para implementar CRUD y patrones de diseño como el DAO. Por lo tanto nos permitirá 
realizar las siguientes acciones desde su menú: </p>

<h3>0. Escriba 0 para salir.</h3>
<p>   Como su nombre lo indica, con esta opción se detiene la ejecución del pograma.</p>

<h3>1. Opciones de CRUD</h3>
<p>   Dentro de este ítem, podemos acceder a un submenú, que nos presenta las siguientes opciones:</p>
      <li>(0) Volver al menu anterior.</li>
      <li>(1) Crear un usuario
          <p>Como su nombre lo indica, en este apartado se podrá agregar un nuevo usuario. En caso de no poseer 
             algun dato, como ser el telefono, se puede enviar un "" en lugar de un valor. Tanto el apeelido como 
             el correo son datos mínimos necesarios.</p></li>
      <li>(2) Modificar un usuario</li>
          <p>Permite modificar tanto el teléfono como el nombre de un usuario según su correo electrónico.</p>
      <li>(3) Eliminar un usuario</li>
          <p>Permite la eliminación de todos los usuarios que tengan un determinado nombre.</p>

<h3>2. Ordenar Lista de usuarios por apellido</h3>

<h3>3. Guardar usuarios en txt</h3>
<p>Permite guardar una lista de usuarios por su apellido y nombre, delimitados por una coma.</p>

<h3>4. Guardar usuarios en archivo .dat</h3>
    <p>Permite guardar un listado de todos los usuarios y sus respectivos datos. Sirve a modo de Backup.</p>

<h3>5. Recuperar lista de usuarios del archivo txt</h3>
    <p>Permite visualizar la lista de usuarios guardada en el paso tres(3).</p>

<h3>6. Recuperar lista de usuarios del archivo .dat</h3>
    <p>Permite visualizar la lista de usuarios guardada en el paso cuatro(4).</p>

<h3>7. Buscar un usuario por id\</h3>
    <p>Busca en la lista de usuarios si existe uno que tenga el id que nosotros ingresamos. Si lo encuentra, muestra sus datos.
    Si no lo encuentra, da un mensaje de aviso.</p>

