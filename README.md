# Proyecto-Gestion-de-Libros
Repositorio que contiene la parte del back del equipo encargado de la gestión de libros del proyecto "Bibliosoft" el cual es un gestor de biblioteca para el colegio Nuestra Señora de la Sabiduría

Se crea el scaffolding del proyecto Library de la siguiente forma: 
```
Library/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/
│   │   │       └── eci/
│   │   │           └── cvds/
│   │   │               ├── LibraryApplication.java
│   │   │               ├── controller/
│   │   │               │   └── LibroController.java
│   │   │               ├── model/
│   │   │               │   └── Ejemplar.java
|   |   |               |   └── Libro.java
│   │   │               ├── repository/
│   │   │               │   └── EjemplarRepository.java
|   |   |               |   └── LibroRepository.java
│   │   │               └── service/
│   │   │                   └── LibroService.java
│   │   └── resources/
│   │       ├── application.properties
└── pom.xml
```

## Creación de la clase libro 📚
Se crea la clase de libro, la cual tiene como atributos los definidos en la propuesta y adicionalmente tendrá una referencia a los ejemplares en otra coleccion de MongoDB

![alt text](images/image-2.png) 

## Configuración EndPoints básicos
Se crearon los endpoint básicos para crear, actualizar, eliminar y consultar libros

- Solicitud POST
![alt text](images/image-3.png)
    - Prueba de la solicitud POST
![alt text](images/image-4.png)


- Solicitud GET
  ![alt text](images/image-5.png)
  - Prueba de la solicitud GET
![alt text](images/image-6.png)


- Solicitud PUT
![alt text](images/image-8.png)
  - Prueba de la solicitud PUT
![alt text](images/image-7.png)


- Solicitud DELETE
![alt text](images/image-10.png)
  - Prueba de la solicitud DELETE
![alt text](images/image-9.png)


## Importación de datos mediante python 👾
![alt text](images/image.png)
El siguiente codigo fué usado para importar los datos que nos fueron otorgados desde un .xlsx a un formato .json para que pudieran ser leídos por un controlador que usando un servicio guarde cada uno de los libros en la base de datos de MongoDB, el servicio fue hecho de la siguiente manera

El servicio es el siguiente:
![alt text](images/image-1.png)

## Despliegue
Se hizo el despliegue con [azure app](https://azure.microsoft.com/es-es/products/app-service)

## Construido con 🛠️
* [Spring Initializer](https://start.spring.io)
* [Maven](https://maven.apache.org/)

## Colaboradores 🖋️
Santiago Diaz Rojas

Juan Sebastian Velasquez Rodriguez

David Felipe Velasquez Contreras

Santiago Alberto Naranjo Abril