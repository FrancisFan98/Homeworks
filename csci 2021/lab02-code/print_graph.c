#include <stdio.h>

void print_graph(int *data, int len, int max_height){
	int nr = len/5;
	int lf = len%5;
	int count = 0;
	
	// find the max and min numbers
	int largest = 0;
	int smallest = 0;
	int numEntry = 0;
	while(numEntry < len){
		if(data[numEntry] > largest){
			largest = data[numEntry];
		}
		else if(data[numEntry] < smallest){
			smallest = data[numEntry];
		}
		else {
			
		}
		numEntry++;
	}
	

	
	int range = largest - smallest;
	int interval = range/max_height;


	
	printf("length : %d\n", len);
	printf("min : %d\n", smallest);
	printf("max : %d\n", largest);
	printf("range : %d\n", range);
	printf("max_height : %d\n", max_height);
	printf("units_per_height : %d\n", interval);
	
	printf("%s", "     ");
	while (count < nr) {
		printf("%s", "+----");
		count++;
	}
	printf("%.*s\n",lf, "+----");
	

	
	//create layers
	int layer[max_height+1];
	for (int i = 0;i <= max_height; i++) {
		layer[i] = largest - i*interval;
	}
	
	// create body
	
	for(int i = 0; i < max_height+1; i++){
		printf("%3d ", layer[i]);
		printf("|");
		for(int j = 0; j < len; j++){
			if(data[j] >= layer[i]){
				printf("%c",'X');
			}
			else {
				printf("%c",' ');
			}
		}
		printf("\n");
	}
	
	count = 0;
	printf("%s", "     ");
	while (count < nr) {
		printf("%s", "+----");
		count++;
	}
	printf("%.*s\n",lf, "+----");
	
	
}