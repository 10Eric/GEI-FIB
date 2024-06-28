#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "socketMng.c"

int main(int argc, char **argv){
	int connectionFD;
	int ret;
	char buffer[80];

	if (argc != 2) {
		strcpy(buffer, "Usage: prClientSocket socketPath\n");
		write(2, buffer, strlen(buffer));
		exit(1);
	}

	connectionFD = clientConnection(argv[1]);
	ret = read(connectionFD, buffer, sizeof(buffer));

	while (ret > 0) {
		write(1, buffer, ret);
		ret = read(connectionFD, buffer, sizeof(buffer));
	}

	closeConnection(connectionFD);

}
