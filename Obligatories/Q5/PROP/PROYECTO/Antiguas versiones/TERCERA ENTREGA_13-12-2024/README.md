# PROP Grup 31.5
Projecte de Programació, Grup 31, subgrup 31.5. <br>Professor: Carles Arnal ([carles.arnal@upc.edu]()).

## Membres del grup

- Eric Díez Apolo ([eric.diez@estudiantat.upc.edu]())
- Pol Carnicer Gonzalez ([pol.carnicer@estudiantat.upc.edu]())
- Aleix Montero Ponce ([aleix.montero@estudiantat.upc.edu]())
- Francesc Pérez Venegas ([francesc.perez.venegas@estudiantat.upc.edu]()).

## Elements del directori

### DOCS:
Conté tota la documentació del projecte: diagrama de casos d'ús amb descripció de cada un d'ells, diagrama estàtic
complet del model conceptual de dades en versió disseny amb breu descripció dels atributs i mètodes de les classes, 
relació de les classes implementades per cada membre de l'equip, descripció de les estructures de dades i algorismes 
emprats per implementar les funcionalitats de l'entrega.

### EXE:
Fitxers executables (*.jar*) de totes les classes que permeten provar les funcionalitats principals implementades.
Hi ha subdirectoris per cada un dels tipus de classes: test, excepcions, funcions, tipus, que segueixen l'estructura
determinada pels *packages*

### FONTS:
Codi de les classes de domini associades a les funcionalitats principals implementades fins al moment. Inclou també els
tests JUnit. Tots els fitxers estan dins dels subdirectoris que segueixen l'estructura de packages, perquè el codi sigui
recompilable directament.


## Provar el programa


Useful commands:


./gradlew test: will run your unit tests. 

./gradlew build: will build the project.

./gradlew compileAllClasses: will compile all classes (main and test) in <project root>/classes/java

./gradlew jarAllDrivers: will create .jar for all drivers in <project root>/EXE

./gradlew jarDriverPerfil: will create jar for DriverPerfil in <project root>/EXE

./gradlew jarDriverPrestatgeria: will create jar for DriverPrestatgeria in <project root>/EXE

./gradlew jarDriverDistribucio: will create jar for DriverDistribucio in <project root>/EXE

./gradlew jar: will create the jar inside the directory <project root>/build/libs with only the project's code. Not dependencies.


./gradlew assembleDist: will create a .tar and a .zip (both contain the same) in the directory <project root>/build/distributions that contain the whole directory structure that will allow to install your project along with its dependencies in a machine without IDE (only with java 11 installed) and run it.


./gradlew clean: will clean the compilation files and the created artifacts

