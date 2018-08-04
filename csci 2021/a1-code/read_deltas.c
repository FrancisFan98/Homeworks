#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>



int *read_text_deltas(char *fname, int *len){
	*len = 0;
	int a;
	FILE* ptr = fopen(fname, "r");
	
	if(ptr == NULL){
		perror("There is a file opening failure");
		fclose(ptr);
		*len = -1;
		return NULL;
	}
	
	while(fscanf(ptr, "%d", &a) != EOF){
		*len+=1;
	}
	
	if (*len == 0) {
		perror("There is no integer in this file");
		fclose(ptr);
		*len = -1;
		return NULL;
	}
	
	rewind(ptr);
	
	int index = 0;
	int *ar = malloc(*len*sizeof(int));
	
	while (index < *len) {
		fscanf(ptr, "%d", &a);
		ar[index] = a;
		if(index > 0){
			ar[index] = ar[index] + ar[index-1];
		}
		index +=1;
	}
	fclose(ptr);
	
	return ar;
}

int *read_int_deltas(char *fname, int *len){
	struct stat buf;
	stat(fname, &buf);
	*len = (int)buf.st_size/4;
	
	if(*len == 0){
		perror("There is no integer in this file");
		*len = -1;
		return NULL;
	}
	
	
	int *ar = malloc(*len*sizeof(int));
	FILE *ptr = fopen(fname, "r");
	if(ptr == NULL){
		perror("There is a file opening failure");
		fclose(ptr);
		*len = -1;
		return NULL;
	}
	int index = 0;
	
	
	while(!feof(ptr)){
		fread(&ar[index], sizeof(int), 1, ptr);
		if(index > 0){
			ar[index] = ar[index-1] + ar[index];
		}
		index++;
	}	
	fclose(ptr);
	
	return ar;
}












