# Directori drivers

> Path absolut: /FONTS/src/main/drivers

## Descripció del directori
En aquest directori es troben totes els drivers de les clases del nostre programa.

## Elements del directori

- **DriverDistribucio:** Executa la part de Distribucio del projecte.

- **DriverPrestatgeria:** Executa la part de Prestatgeria del projecte.

- **DriverPerfil:** Executa la part de Perfil del projecte.

- **Main:** Executa tots els drivers en 1, i que hi hagi traspas de dades 1 entre l'altre.

## DriverDistribucio

### Atributos de la clase

1. **controladorDistribucio**: Es una instancia de la clase ControladorDistribucio que se utiliza para gestionar la distribución de productos.

2. **scanner**: Es una instancia de la clase Scanner que se utiliza para leer la entrada del usuario desde la consola.

### Funciones de la clase

- **main(String[] args):**  Punto de entrada del programa. Llama a crearDistribucionInicial() y mostrarOpcionesDistribucio() para iniciar la aplicación.

- **crearDistribucionInicial():**  Solicita al usuario que introduzca la altura del estante predeterminado y crea una distribución inicial con la altura dada.

- **mostrarOpcionesDistribucio():**   Muestra un menú de opciones al usuario y maneja la elección del usuario llamando al método correspondiente.

- **consultaDistribucio_Ordenada_Actual():**   Verifica si hay productos en la distribución. Si es así, imprime la matriz de distribución actual y pregunta al usuario si desea editarla.

- **calcular_distribucion():**  Verifica si hay productos en la distribución. Si es así, solicita al usuario que elija una estrategia de cálculo (Backtracking o TSP) y calcula la distribución utilizando la estrategia elegida.

- **imprimirDistro(String[][] matrix):**  Imprime la matriz de distribución de manera formateada.

- **consultaProductes():**  Imprime la lista de productos en la distribución.

- **agregarProducte():** Solicita al usuario que introduzca los detalles de un nuevo producto y lo añade a la distribución Opcionalmente, permite al usuario modificar las similitudes del producto.

- **eliminarProducte():** Solicita al usuario que seleccione un producto y lo elimina de la distribución.

- **modificarProducte():** Solicita al usuario que seleccione un producto y modifique su precio y cantidad.

- **modificaSimilituds():** Solicita al usuario que seleccione un producto y modifique sus similitudes con otros productos.

- **imprimeix_productes_i_get_producte():** Imprime la lista de productos y solicita al usuario que seleccione uno. Devuelve el nombre del producto seleccionado.

- **modificaSimilitudProducte(String Producto):** Solicita al usuario que seleccione un producto y establezca su similitud con otro producto.

- **imprimeSimilitud(String producto):** Imprime las similitudes del producto dado con otros productos y devuelve la lista de similitudes.

- **imprimeSimilituds():** Imprime las similitudes de todos los productos en la distribución.

- **consulta_info_producte(String producte):** Imprime los detalles (nombre, marca, precio, stock) del producto dado.

- **intercanviaProductes():** Solicita al usuario que seleccione dos productos y los intercambia en la distribución.

- **error_no_hi_ha_productes():** Imprime un mensaje de error indicando que no hay productos.

- **pedirEntero():** Solicita al usuario que introduzca un número entero y lo devuelve. Maneja la entrada no válida.

- **pedirDouble():** Solicita al usuario que introduzca un número decimal y lo devuelve. Maneja la entrada no válida.


## DriverPrestatgeria

### Atributos de la clase

1. **perfilActual**: Es una instancia de la clase `Perfil` que representa el perfil del usuario actual.

2. **prestatgeria**: Es un mapa que asocia nombres de estanterías con sus respectivos controladores (`ControladorPrestatgeria`).

3. **prestatgeriaActual**: Es una instancia de la clase `Prestatgeria` que representa la estantería actualmente seleccionada.

4. **scanner**: Es una instancia de la clase `Scanner` que se utiliza para leer la entrada del usuario desde la consola.

### Funciones de la clase

- **main(String[] args)**: Punto de entrada del programa. Inicializa el perfil del usuario, carga las estanterías guardadas y muestra el menú principal.

- **mostrarMenuPrestatgerias()**: Muestra un menú de opciones al usuario y maneja la elección del usuario llamando al método correspondiente.

- **mostrarFormularioCrearPrestatgeria()**: Solicita al usuario que introduzca los detalles de una nueva estantería y la añade al perfil actual.

- **mostrarFormularioEditarPrestatgeria()**: Solicita al usuario que seleccione una estantería y permite modificar su nombre y altura.

- **mostrarFormularioCargarPrestatgeria()**: Solicita al usuario que seleccione una estantería guardada y la carga en el perfil actual.

- **mostrarFormularioBorrarPrestatgeria()**: Solicita al usuario que seleccione una estantería y la elimina del perfil actual.

- **pedirEntero()**: Solicita al usuario que introduzca un número entero y lo devuelve. Maneja la entrada no válida.

- **pedirDouble()**: Solicita al usuario que introduzca un número decimal y lo devuelve. Maneja la entrada no válida.

- **guardarPrestatgerias()**: Guarda las estanterías del perfil actual en un archivo.

- **cargarPrestatgerias()**: Carga las estanterías guardadas desde un archivo al perfil actual.


## DriverPerfil

### Atributos de la clase

1. **perfiles**: Es un mapa que asocia nombres de usuarios con sus respectivos controladores (`ControladorPerfil`).

2. **perfilActual**: Es una instancia de la clase `ControladorPerfil` que representa el perfil actualmente seleccionado.

3. **scanner**: Es una instancia de la clase `Scanner` que se utiliza para leer la entrada del usuario desde la consola.

### Funciones de la clase

- **main(String[] args)**: Punto de entrada del programa. Carga los datos guardados y muestra el menú principal.

- **mostrarMenuPrincipal()**: Muestra un menú de opciones al usuario y maneja la elección del usuario llamando al método correspondiente.

- **mostrarFormularioCrearPerfil()**: Solicita al usuario que introduzca los detalles de un nuevo perfil y lo añade al mapa de perfiles.

- **mostrarFormularioCargarPerfil()**: Solicita al usuario que introduzca su nombre de usuario y contraseña, y carga el perfil correspondiente si las credenciales son correctas.

- **mostrarFormularioBorrarPerfil()**: Solicita al usuario que seleccione un perfil y lo elimina del mapa de perfiles.

- **mostrarFormularioEditarPerfil()**: Solicita al usuario que seleccione un perfil y permite modificar su nombre de usuario o contraseña.

- **pedirEntero()**: Solicita al usuario que introduzca un número entero y lo devuelve. Maneja la entrada no válida.

- **pedirDouble()**: Solicita al usuario que introduzca un número decimal y lo devuelve. Maneja la entrada no válida.

- **guardarDatos()**: Guarda los perfiles en un archivo.

- **cargarDatos()**: Carga los perfiles guardados desde un archivo.

