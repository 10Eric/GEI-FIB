#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

void halt(char c[]) {
	perror(c);
	exit(0);
}

void Usage(void) {
	char buff[] = "Usage: ,/append [arg0]";
	write(1, buff, strlen(buff));
}

int main(int argc, char **argv) {
	if (argc < 2) Usage();
	else {
		int fd, ini, fi;
		char buff[128];
		if ((fd = open(argv[1], O_RDWR)) < 0) halt("open");

		ini = 0;
		if ((fi = lseek(fd, 0, SEEK_END)) < 0) halt("lseek");
		//sprintf(buff, "%d\n", fi);
		//write(1, buff, strlen(buff));
		char c;
		lseek(fd, ini, SEEK_SET);
		while ((read(fd, &c, sizeof(char) > 0)
			&& ini < fi)) {
			lseek(fd, 0, SEEK_END);
			write(fd, &c, sizeof(char));
			++ini;
			lseek(fd, ini, SEEK_SET);
		}
	}
}
