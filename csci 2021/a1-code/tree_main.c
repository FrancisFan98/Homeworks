#include <stdio.h>
#include "tree.h"
#include <string.h>
#include <stdlib.h>

// global variable to control echoing, 0: echo off, 1: echo on
int ECHO = 0;                   

// Print the given string if echoing is turned on
void echo(char *s){
	if(ECHO){
		printf("%s",s);
	}
}



int main(int argc, char *argv[]) {
	if(argc > 1 && strcmp("-echo",argv[1])==0) {
		ECHO=1;
	}
	
	

	printf("BST Demo\n");
	printf("Commands:\n");
	printf("  print:          shows contents of the tree in sorted order\n");
	printf("  show:           shows contents in pre-order indicating its structure\n");
	printf("  clear:          eliminates all elements from the tree\n");
	printf("  quit:           exit the program\n");
	printf("  add <name>:     inserts the given string into the tree, duplicates ignored\n");
	printf("  find <name>:    prints FOUND if the name is in the tree, NOT FOUND otherwise\n");
	printf("  save <file>:    writes the contents of the tree in post order to the given file\n");
	printf("  load <file>:    clears the current tree and loads the one in the given file\n");



	bst_t tree;
	bst_init(&tree);
	char cmd[128];
	char buf[10] = "BST>";
	while(1){
		echo(buf); echo(" ");
		int success = fscanf(stdin,"%s",cmd); 
		
		
		if(success==EOF){                 // check for end of input
			printf("\n");                   // found end of input
			bst_clear(&tree);
			break;                          // break from loop
		}

		if( strcmp("quit", cmd)==0 ){     // check for exit command
			echo(cmd);  echo(" ");
			printf("\n");
			bst_clear(&tree);
			break;                          // break from loop
		}

		else if( strcmp("print", cmd)==0 ){ // print
			echo(cmd);  echo(" ");
			echo("\n");
			bst_print_inorder(&tree);
		}

		else if( strcmp("show", cmd)==0 ){     // show command
			echo(cmd);  echo(" ");
			printf("\n");
			bst_print_preorder(&tree);
		}

		else if( strcmp("clear", cmd)==0 ){   // clear command
			echo(cmd);  echo(" ");
			printf("\n");
			bst_clear(&tree);
		}

		else if( strcmp("add", cmd)==0 ){   // add command
			echo(cmd);  echo(" ");
			fscanf(stdin,"%s",cmd); 
			echo(cmd);echo("\n");
			bst_insert(&tree, cmd);
		}
		else if( strcmp("find", cmd) == 0){
			echo(cmd);  echo(" ");
			fscanf(stdin,"%s",cmd);
			echo(cmd);printf("\n");
			if(bst_find(&tree, cmd) == 1){
				printf("FOUND\n");
			}
			else{
				printf("NOT FOUND\n");
			}
		}
		else if(strcmp("save", cmd) == 0){
			echo(cmd);  echo(" ");
			fscanf(stdin, "%s", cmd);
			echo(cmd);
			printf("\n");
			bst_save(&tree, cmd);
		}
		else if(strcmp("load", cmd) == 0){
			echo(cmd);  echo(" ");
			fscanf(stdin, "%s", cmd);
			echo(cmd);echo("\n");
			bst_load(&tree, cmd);
		}
		else{
			
		}
		
	}
	
}