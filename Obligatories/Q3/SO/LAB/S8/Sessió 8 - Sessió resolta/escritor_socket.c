#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "socketMng.c"

int main(int argc, char **argv){
	int socketFD;
	int connectionFD;
	int ret;
	char buffer[80];

	if (argc != 2) {
		strcpy(buffer, "Usage: prServerSocket socketPath\n");
		write(2, buffer, strlen(buffer));
		exit(1);
	}

	socketFD = createSocket(argv[1]);
	connectionFD = serverConnection(socketFD);
	ret = read(0, buffer, sizeof(buffer));

	while (ret > 0) {
		write(connectionFD, buffer, ret);
		ret = read(0, buffer, sizeof(buffer));
	}

	closeConnection(connectionFD);
	deleteSocket(socketFD, argv[1]);

}
