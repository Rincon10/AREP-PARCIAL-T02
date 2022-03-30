# AREP-PARCIAL-T02

Diseñe un prototipo de calculadora de microservicios que tenga un servicios de matemáticas con al menos dos funciones implementadas y desplegadas en al menos dos instancias virtuales de EC2. Además debe implementar un service proxy que recibe las solicitudes de servicios y se las delega a las dos instancias usando un algoritmo de round-robin. Asegúrese que se pueden configurar las direcciones y puertos de las instancias en el porxy usando variables de entorno del sistema operativo. Cada estudiante debe seleccionar para desarrollar dos funciones matemáticas de acuerdo a los dos últimos dígitos de su cédula como se especifica en la lista (Si sus dos últimos dígitos de su cédula son el mismo use el siguiente dígito que sea diferente). Todas las funciones reciben un solo parámetro de tipo "Double" y retornan una parámetro de tipo "Double".


<img src="https://github.com/Rincon10/AREP-PARCIAL-T02/blob/master/images/Arquitectura.jpg" />


0. log
1. ln
2. sin
3. cos
4. tan
5. acos
6. asin
7. atan
8. sqrt
9. exp (el número de eauler elevado ala potendia del parámetro)


Implemente los servicios para responder al método de solicitud HTTP GET. Deben usar el nombre de la función especificado en la lista y el parámetro debe ser pasado en la variable de query con nombre "value".

El proxy debe delegar el llamado a los servicios de backend. El proxy y los servicios se deben implementar en Java usando Spark.


## Ejemplo de una llamado:

EC2
https://amazonxxx.x.xxx.x.xxx:{port}/cos?value=3.141592

Salida. El formato de la salida y la respuesta debe ser un JSON con el siguiente formato

{

 "operation": "cos",

 "input":  3.141592,

 "output":  -0.999999

}


## Entregable:

1. Proyecto actualizado en github (uno o dos repositorios, incluya referencias al repositorio alterno en el repositorio que entrega)

2. Descripción del proyecto en el README con pantalazos que muestren el funcionamiento.

3. Descripción de como correrlo en EC2.

4. Video de menos de un minuto del funcionamiento (lo puede tomar con el celular una vez funcione)


## **Prerrequisitos**

-   [Git](https://git-scm.com/downloads) - Sistema de control de versiones
-   [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias
-   [Java 8](https://www.java.com/download/ie_manual.jsp) - Entorno de desarrollo
-   [Intellij Idea](https://www.jetbrains.com/es-es/idea/download/) (Opcional)
-   [Docker](https://www.docker.com/get-started) -  Motor para contenedores


## Video de funcionamiento del RoundRobin (Verlo desde el segundo 26)

En este video se muestra de manera local como el algoritmo de balanceador de carga cumple su objetivo, distribuyendo las peticiones entre dos instancias de spark que corren a diferentes puertos, 30001 y 30002 respectivamente.

Como se aprecia en el [video](https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/r/personal/ivan_rincon-s_mail_escuelaing_edu_co/Documents/Recordings/Call%20with%20LAURA%20and%201%20other-20220330_172234-Meeting%20Recording.mp4?csf=1&web=1&e=3mIUSw), se ve que cada vez que se actualiza la petición al proxy el url que este usa va variando el puerto.

## Video de funcionamiento en AWS con uso de dockers (Ver video desde el minuto 2:53)

En este [video](https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/r/personal/ivan_rincon-s_mail_escuelaing_edu_co/Documents/Recordings/Llamada%20con%20LAURA%20y%201%20m%C3%A1s-20220330_173338-Grabaci%C3%B3n%20de%20la%20reuni%C3%B3n.mp4?csf=1&web=1&e=HQmgOy) se muestra ya el funcionamiento de la arquitectura propuesta ya montada en AWS, en este se va llamando cada una de las peticiones de las diferentes máquinas EC2, una de estas EC2(corre por el puerto 3000)-proxy y otra EC2(corre por el puerto 3001-3002) - Funciones aSin y aTan, ademas a lo largo de este se muestra que imagenes e instancias se utilizaron para lograrlo.

## Imagenes
Para ver imagenes del procesos revisar [aquí](https://github.com/Rincon10/AREP-PARCIAL-T02/tree/master/images) 
