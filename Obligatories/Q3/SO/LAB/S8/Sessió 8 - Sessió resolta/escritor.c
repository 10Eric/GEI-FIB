#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc, char **argv){
	char buff[80];
	int fd = open("./pipe",O_WRONLY);
	int ret;
	while((ret = read(fd,buff,ret)) > 0){
		write(fd,buff,ret);
	}
}
