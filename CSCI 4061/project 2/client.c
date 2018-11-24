#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/un.h>
#include <sys/wait.h>
#include <sys/socket.h>
#include "comm.h"
#include "util.h"

/* -------------------------Main function for the client ----------------------*/
void main(int argc, char * argv[]) {

	int pipe_user_reading_from_server[2];//pipe_user_reading_from_server
	int pipe_user_writing_to_server[2];//pipe_user_writing_to_server
	char buf[MAX_MSG]; 

	// You will need to get user name as a parameter, argv[1].

	if(connect_to_server("group66", argv[1], pipe_user_reading_from_server, pipe_user_writing_to_server) == -1) {
		exit(-1);
	}
	print_prompt(argv[1]);
	//non-block
	fcntl(0, F_SETFL, fcntl(0, F_GETFL)| O_NONBLOCK);
	fcntl(pipe_user_reading_from_server[0], F_SETFL, fcntl(pipe_user_reading_from_server[0], F_GETFL)| O_NONBLOCK);
	//close unused end
	close(pipe_user_reading_from_server[1]);
	close(pipe_user_writing_to_server[0]);
	while (1) {
		usleep(1000);
		int nbytes2=read(pipe_user_reading_from_server[0],buf,MAX_MSG);
		//printf("nbytes2 is %d\n",nbytes2);
		if(nbytes2<0 && errno!=EAGAIN) {
			perror("error reading data from pipe");
			exit(-1);
        }
        if (nbytes2>0) {
			 printf("%s\n", buf);
			 memset(buf,'\0', MAX_MSG);
			 print_prompt(argv[1]);
        }
        if(nbytes2==0) {
        	exit(-1);
        }

		int nbytes1=read(0,buf,MAX_MSG);
		if(nbytes2<0 && errno!=EAGAIN) {
			perror("error reading data from user terminal");
        	exit(-1);
        }

        if(nbytes1==0) {
        	continue;
        	print_prompt(argv[1]);
        }

        if (nbytes1>0) {
        	buf[strlen(buf)-1]='\0';
			if (write(pipe_user_writing_to_server[1],buf,strlen(buf))<0) {
				//printf("error writing to pipe\n");
				exit(-1);
			}
			// if(strcmp(buf,"\\exit")||strcmp(buf,"\\seg")) {
			// 	exit(0);
			// }
		
			memset(buf,'\0', MAX_MSG);
			print_prompt(argv[1]);
        }


        //poll pipe

    }
   
		
 

	/* -------------- YOUR CODE STARTS HERE -----------------------------------*/

	
	// poll pipe retrieved and print it to sdiout

	// Poll stdin (input from the terminal) and send it to server (child process) via pipe

		
	/* -------------- YOUR CODE ENDS HERE -----------------------------------*/
}

/*--------------------------End of main for the client --------------------------*/


