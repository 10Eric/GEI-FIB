OPCIONS = -D_GLIBCXX_DEBUG -O2 -Wall -Wextra -Werror -Wno-uninitialized -Wno-sign-compare -std=c++11
#
main.exe: main.o Cluster.o Procesador.o Proceso.o Sala_Espera.o Prioridad.o
	g++ -o main.exe main.o Cluster.o Procesador.o Proceso.o Sala_Espera.o Prioridad.o
#
Cluster.o: Cluster.cc 
	g++ -c Cluster.cc $(OPCIONS)
Procesador.o: Procesador.cc 
	g++ -c Procesador.cc $(OPCIONS)
Proceso.o: Proceso.cc
	g++ -c cromosoma.cc $(OPCIONS)
main.o: main.cc
	g++ -c main.cc $(OPCIONS)
Sala_Espera.o: Sala_Espera.cc
	g++ -c Sala_Espera.cc $(OPCIONS)
Prioridad.o: Prioridad.cc
	g++ -c Prioridad.cc $(OPCIONS)
#


tar:
	tar -cvf practica.tar *.cc *.hh Makefile

clean:
	rm *.o
	rm *.exe
	rm *.tar
