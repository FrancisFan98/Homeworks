#include <stdio.h>
#include "tree.h"
#include <stdlib.h>

int main(int argc, char *argv[]) {
	node_t *a = malloc(sizeof(node_t));
	printf("%d", a->left);
}