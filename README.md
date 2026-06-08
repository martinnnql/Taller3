Descripción del proyecto
Este proyecto es un sistema de gestión desarrollado en Java (utilizando Programación Orientada a Objetos) para administrar un mundo mágico compuesto por magos y sus respectivos hechizos. El sistema permite clasificar los hechizos en cuatro elementos (Fuego, Tierra, Planta y Agua), calculando puntuaciones específicas según sus propiedades. La aplicación cuenta con un Panel de Administrador para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los datos, los cuales persisten en archivos .txt, y un Panel de Analista que permite visualizar rankings y estadísticas detalladas del sistema.

Martin Alonso Arancibia Alzamora - 22.273.853-9 - @martinnnql

Estructura del proyecto (paquetes y clases principales)
El proyecto se organiza bajo una arquitectura modular dividida en dos paquetes principales:

- dominio: Contiene las clases de modelo y entidades.

- Hechizo.java (Clase abstracta base).

- HechizoFuego.java, HechizoAgua.java, HechizoPlanta.java, HechizoTierra.java (Subclases).

- Mago.java (Clase que gestiona la relación con los hechizos).

- logica: Contiene la lógica del sistema y el flujo de la aplicación.

- App.java (Clase principal que contiene el main y la lógica de menús).

- ISystem.java (Interfaz que define los comportamientos del sistema).

- SystemImpl.java (Clase que implementa la interfaz con la lógica funcional).

Instrucciones de ejecución
1. Clonar el repositorio en tu máquina local.

2. Abrir el proyecto en Eclipse (o tu IDE de preferencia).

3. Asegurarse de que los archivos Magos.txt y Hechizos.txt se encuentren ubicados en la raíz del proyecto (al mismo nivel que la carpeta src).

4. Ejecutar la clase App.java que se encuentra dentro del paquete logica.

5. Seguir las instrucciones en consola para navegar entre el Panel de Administrador y el Panel de Analista.
