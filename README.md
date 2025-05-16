# Proyecto CRUD (educativo)

<div align='center'>
<img src="https://i.ibb.co/MkqMKtLs/ba-aprende-logo.png" alt="logo ba-aprende">
</div>

## Talento Tech (Pre-entrega)

- Autor del proyecto: **Díaz, Lautaro Nicolás**
- Duración del curso: **4 meses**
- Año: **2025**

### Acerca del proyecto

El proyecto final consiste en realizar una API Rest desarrollado en **Java** utilizando el framework **Spring** para hacer tanto la conexión a la base de datos como el desarrollo de los endpoints.

En esta primera entrega, se realiza una aplicación de consola utilizando una entidad (Producto) que tenga su correspondiente CRUD con validaciones de tipos de datos, y validación del dato de entrada del usuario. Además, incorporé una entidad extra (Pedido) donde almacena una lista de productos e informa la suma total de los productos.

```java
        ***********************************************
        ************   MENU DE OPCIONES   *************
        ***********************************************
        ** Escriba el número de la operación deseada **

        1) Crear un producto
        2) Listar productos
        3) Buscar/actualizar un producto
        4) Eliminar un producto
        5) Crear un pedido
        6) Listar pedidos

        9) Salir

        Elija una opción:
```

### ¿Qué se puede hacer en el proyecto de consola?

- El usuario puede registrar un Producto que contiene: nombre, precio y una breve descripción de hasta 100 caracteres. Cada producto tiene un ID que se genera automáticamente y así se distingue de los demás.
- El usuario puede acceder a la información de todos los productos registrados.
- El usuario puede eliminar y/o modificar un producto registrado (no podrá acceder a este sub-menú si no existen productos registrados).

```java
        **********************************************************
        ************   ADMINISTRACIÓN DE PRODUCTOS   *************
        **********************************************************
        ******* Escriba el número de la operación deseada ********

            ----- Productos actualmente registrados -----
        // Aquí cada uno de los productos (si existen)

        1) Mostrar información completa del producto
        2) Actualizar un producto

        9) Volver

        Elija una opción:
```

- Es posible registrar un pedido que almacena una lista de productos, el usuario accede a un submenú donde tiene un manera más cómoda de registrar productos o quitarlos del pedido (no podrá agregar productos si no existen ninguno registrado, y tampoco podrá confirmar un pedido sin productos).

```java
        ********************************************************
        ************   ADMINISTRACIÓN DE PEDIDOS   *************
        ********************************************************
        ****** Escriba el número de la operación deseada *******    
        
        -- PEDIDO GENERADO -- ID: /*Aquí el ID autogenerado */ --

        1) Agregar productos al pedido
        2) Quitar productos al pedido
        
        5) CONFIRMAR PEDIDO

        9) CANCELAR PEDIDO

        Elija una opción:
```

- Por último, el usuario puede visuarlizar todos los pedidos, mostrando un breve detalle de los productos que contiene y la suma total de los mismos.

## ¿Cómo utilizar este proyecto?

- Descargar **Visual Studio Code** y abrir este repositorio como carpeta de proyecto.
- Utilizar Java JDK 17 o superior.
- Iniciar el proyecto desde el archivo Main.java