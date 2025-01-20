# PROP Grup 31.5
Projecte de Programació, Grup 31, subgrup 31.5. <br>Professor: Carles Arnal ([carles.arnal@upc.edu]()).

## Membres del grup

- Eric Díez Apolo ([eric.diez@estudiantat.upc.edu]())
- Pol Carnicer Gonzalez ([pol.carnicer@estudiantat.upc.edu]())
- Aleix Montero Ponce ([aleix.montero@estudiantat.upc.edu]())
- Francesc Pérez Venegas ([francesc.perez.venegas@estudiantat.upc.edu]()).

## Elements del directori

### DOCS:
Conté tota la documentació del projecte: el Manual d’usuari, la Relació de les classes implementades per cada membre de l’equip, 
i un document que decriu exhaustivament les proves efectuades.

### EXE:
Fitxers executables (*.jar*) de totes les classes que permeten provar les funcionalitats principals implementades.
Hi ha subdirectoris per cada un dels tipus de classes: test, excepcions, funcions, tipus, que segueixen l'estructura
determinada pels *packages*

### FONTS:
Codi de les classes de domini associades a les funcionalitats principals implementades fins al moment. Inclou també els
tests JUnit. Tots els fitxers estan dins dels subdirectoris que segueixen l'estructura de packages, perquè el codi sigui
recompilable directament.

### JAVADOC:
Documentació de les classes i mètodes del projecte en JAVADOC.

### PERFILES:
Conté tota l'informacio (en .dat) dels perfils que es guarden en la aplicació.

### PRESTATGERIES:
Conté tota l'informacio (en .dat) de les prestatgeries que es guarden en la aplicació.


## Provar el programa


Useful commands:

./gradlew run: will run the application.

./gradlew test: will run your unit tests. 

./gradlew build: will build the project.

./gradlew compileAllClasses: will compile all classes (main and test) in <project root>/classes/java

./gradlew jar: will create the jar inside the directory <project root>/build/libs with only the project's code. Not dependencies.


./gradlew assembleDist: will create a .tar and a .zip (both contain the same) in the directory <project root>/build/distributions that contain the whole directory structure that will allow to install your project along with its dependencies in a machine without IDE (only with java 11 installed) and run it.


./gradlew clean: will clean the compilation files and the created artifacts

