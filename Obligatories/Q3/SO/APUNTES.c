#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <error.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

**************************
    ERRORES
***************************
void Usage() {
    error(1,0, "Invalid argument. \n Correct usage: ./nproc_time_max PID1 PID2 ... PIDN");
}

error(1, errno, "mknod");


**************************
    PIPES CON NOMBRE
***************************
mknod("./PipeA", S_IRUSR|S_IWUSR|__S_IFIFO, 0)
mkfifo("./PipeA", S_IRUSR|S_IWUSR)

if ((fd2 = open("PipeA" ,O_WRONLY)) < 0)  //ESCRITURA
if ((fd1 = open("PipeA", O_RDONLY)) < 0) // LECTURA

ret = read(fd1, number, sizeof(number))) < 0)

if (pid == 0) {
        // Proceso hijo (escritor)
        int fd = open("mi_fifo", O_WRONLY);

        // Escribir en la tubería con nombre desde el proceso hijo
        write(fd, "Hola desde el proceso hijo (escritor)", 38);

        // Cerrar la tubería con nombre en el proceso hijo
        close(fd);
    } else {
        // Proceso padre (lector)
        int fd = open("mi_fifo", O_RDONLY);

        // Leer desde la tubería con nombre en el proceso padre
        char buffer[256];
        read(fd, buffer, sizeof(buffer));
        printf("Proceso padre (lector) recibió: %s\n", buffer);

        // Cerrar la tubería con nombre en el proceso padre
        close(fd);


**************************
    PIPES SIN NOMBRE
***************************
int *fd = malloc(2*sizeof(int)); /// free(fd);
pipe(fd);

//O

int pipefd[2];
pipe[pipefd];


dup2(fd[1], 1);


if (pid == 0) {
        // Proceso hijo
        close(pipefd[1]);  // Cerrar el extremo de escritura de la tubería en el proceso hijo
        // Leer desde la tubería
        read(pipefd[0], buffer, sizeof(buffer));
        printf("Proceso hijo recibió: %s\n", buffer);
        close(pipefd[0]);
    } else {
        // Proceso padre
        close(pipefd[0]);  // Cerrar el extremo de lectura de la tubería en el proceso padre
        // Escribir en la tubería
        write(pipefd[1], "Hola desde el proceso padre", 26);
        close(pipefd[1]);
    }

**************************
    WAITPID
***************************
if (waitpid(pid, &exit_stat, 0) < 0) error(1, errno, "waitpid"); /// waitpid(-1, &exit_stat, 0)
if (WIFEXITED(exit_stat)) exit_code = WEXITSTATUS(exit_stat);



**************************
    EXECLP
***************************
execlp("./proc_time","proc_time", argv[i], NULL);



**************************
    SIGNALS
***************************
sigset_t mask;
struct sigaction trat;
trat.sa_handler = trat_usr1;
trat.sa_flags = 0;              //Not necessary
if (sigaction(SIGUSR1, &trat, NULL) < 0) error(1, errno, "sigaction");
sigfillset(&mask);
sigdelset(&mask,SIGUSR1);
sigsuspend(&mask);


sigemptyset(&mask);
sigaddset(&mask, SIGUSR1);
if (sigprocmask(SIG_SETMASK, &mask, NULL) < 0)

**************************
    ATOI
***************************

number[ret] = '\0'; //SI ES UN VECTOR DE CARACTERES PON UN \0 AL FINAL
t = atoi(number);


**************************
    ESCRIBIR
***************************
sprintf(buff, "Max_time: %d\n",max_time);
if (write(1,buff, strlen(buff)) < 0) error(1, errno, "write");


