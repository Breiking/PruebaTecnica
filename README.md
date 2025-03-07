# Getting Started

### Reference Documentation

Para que los links funcionen el proyecto debe estar corriendo

La Api tiene una breve descriocion de los Json en la documentacion de [Swagger](http://localhost:8080/Gestion/api/v1/swagger-ui/index.html)

Link de la api, a este link se le añaden los endpoints de que aparecen en Swagger [Api](http://localhost:8080/Gestion/api/v1/)

### Json en caso de querer usar PostMan o otra herramienta para el uso del API

#### Json para tareas

para crear una nueva tarea con el estado pendiente o Sin asignar el Json es: 
{
  "titulo": "string",
  "descripcion": "string"
}

en caso de crear una tarea y querer asignar un usario y cambiar el estado por defecto de la tarea o modificar una tarea ya existente, el Json seria 
{
  "titulo": "string",
  "descripcion": "string",
  "fk_user": {
    "id": 0
  },
  "fk_estado": {
    "id": 0
  }
}

#### Json para Users

el Json es el mismo tanto para crear como para actualizar un usuario 
{
  "nombre": "string",
  "email": "string"
}

#### Json para Estados

el Json es el mismo tanto para crear como para actualizar un usuario 
{
  "nombre": "string",
  "descripcion": "string"
}

### Gestion de errores

Los errores mas comunes se ven reflejadoes en el response, otros como las validaciones se ven reflejados unicamente en la consola


### Base de datos

Descargar la base de datos [Descargar gestiontareas.sql](https://github.com/Breiking/PruebaTecnica/releases/download/v1.0.0/gestiontareas.sql)

En caos de que el link anterior no sirva, el archivo .sql con la base datos ira dentro del archivo .rar que le compartire por el Teams esta debera tener el nombre gestiontareas, esta ya tendra 4-5 datos dentro de cada tabla para poder realizar las pruebas
