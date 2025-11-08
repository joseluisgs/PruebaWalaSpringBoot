# PruebaWalaSpringBoot

Proyecto de como hacer una tienda de segunda mano usando Spring Boot y otras cosillas.

Este proyecto es una migración completa del repositorio [WalaSpringBoot2020](https://github.com/joseluisgs/WalaSpringBoot2020).

## Tecnologías
* H2 como base de datos para que quien lo quiera no usar MySQL, luego lo cambiaré
* Spring Security
* Spring Sessions
* Thymeleaf

## Desarrollo
* 14/01/2020: Inicio del proyecto y configuración del mismo: application.properties
* 14/01/2020: Configuracion de pomxm. H2, seguridad, webjars
* 17/01/2020: Modelos, Repositorios
* 17/01/2020: Configuracion de la seguridad: SeguridadConfig y UserDetailsImpl
* 17/01/2020: Servicios: Usuario, Producto, Compra
* 18/01/2020: Plantillas, estilos y Fragmentos de Plantillas en index.html. Recursos estaticos (actualizada dir de seguridad)
* 20/01/2020: Platilla Carrusel, Login, Registro (2 en uno con JS), Producto, todo lo relacionado con compra.
* 20/01/2020: Controladores: LoginController, ZonaPublica (listado y ficha de producto), CompraController (con PDF itext)
* 21/01/2020: Otra forma de hacer PDFs usando Thymeleaf como plantilla del PDF, recomendado
* 22/01/2020: Producto nuevo y modificar si no se ha vendido. Eliminar producto. Validaciones internas

## Ejecución
http://localhost:8080

H2 Console: http://localhost:8080/h2-console
* user: sa
* db: jdbc:h2:./walaspringboot

## Usuario de ejemplo
* usuario: prueba@prueba.com - pass: prueba
* usuario: otro@otro.com - pass: otro

## Acerca de
José Luis González Sánchez: [@joseluisgonsan](https://twitter.com/joseluisgonsan)
