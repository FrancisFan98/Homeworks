#include <stdio.h>
#include <string.h>
#include "tree.h"
#include <stdlib.h>

void bst_init(bst_t *tree){
	// Initialize the tree to have a null root  nodes and have size 0.
	tree->root = NULL;
	tree->size = 0;
}

int node_find(node_t *cur, char name[]){
// Recursive helper function that uses binary search on tree rooted at
// node.  Returns 1 if the name is found somewhere in this tree and 0
// otherwise. Checks the current node for name. If not found, chooses
// left or right subtrees to search according to the sorting order of
// the name compared to the cur node.
	if(cur == NULL){
		return 0;
	}
	
	if(strcmp(cur->name, name) == 0){
		return 1;
	}
	else if(strcmp(cur->name, name) < 0){
		return node_find(cur->right, name);
	}
	else {
		return node_find(cur->left, name);
	}

}

int bst_find(bst_t *tree, char name[]){
// Determines whether name is present or not in the tree using binary
// search. Returns 1 if name is present and 0 otherwise. Uses helper
// node_find().
	node_t *cur = tree->root;

	return node_find(cur, name) ;
	
	
}

node_t *node_insert(node_t *cur, char name[]){
// Recursive helper function to insert the given name in the tree
// rooted at cur. If cur is NULL, allocates a new node, copies name
// into it, and returns the new node. If cur is not NULL, checks for
// differences between name and cur.name. For identical data, returns
// NULL.  Otherwise recurses left or right to link a new node. Returns
// the node cur to stitch the tree back together as the recursion
// unwinds.
	node_t *insert = malloc(sizeof(node_t));
	

	if(cur == NULL){
		cur = insert;
		strcpy(cur->name, name);
		cur->left = NULL;
		cur->right = NULL;
		
		return cur;
	}
	if(strcmp(cur->name, name) == 0){
		return NULL;
	}
	else if(strcmp(cur->name, name) < 0){
		cur->right = node_insert(cur->right, name);
		}
	else {
		cur->left = node_insert(cur->left, name);
		
		
	}
	free(insert);
	return cur;	
}

int bst_insert(bst_t *tree, char name[]){
	// Inserts name into the specified BST. If the name already exists in
	// the tree, returns 0 and does not change the BST. Otherwise inserts
	// the name in a new node, increments the size of the tree, and
	// returns 1. Makes use of the recursive node_insert function.
	node_t *cur = tree->root;
	
	cur = node_insert(cur, name);
	if(cur == NULL){
		return 0;
	}
	tree->root = cur;
	
	tree->size += 1;
	return 1;	
	
}

void node_remove_all(node_t *cur){
// Recursive helper function which visits all nodes in a tree and
// frees the memory associated with them. This requires a post-order
// traversal: visit left tree, visit right tree, then free the cur
// node.
	if(cur == NULL){
		return;
	}
	if(cur->left != NULL){
		node_remove_all(cur->left);
	}
	if(cur->right != NULL){
		node_remove_all(cur->right);
	}
	free(cur);
	return;
}

void bst_clear(bst_t *tree){
// Eliminate all nodes in the tree setting its contents empty. Uses
// recursive node_remove_all() function to free memory for all nodes.
	node_t *cur = tree->root;
	node_remove_all(cur);
	tree->root = NULL;
	tree->size = 0
	return;
}

void node_print_inorder(node_t *cur){
// Recursive helper function which prints all elements in the tree
// rooted at cur in order: traverses left subtree, prints cur node,
// traverses right tree.
	if(cur == NULL){
		return;
	}
	if(cur->left != 0){
		node_print_inorder(cur->left);
	}
	printf("%s\n",cur->name);
	if(cur->right != 0){
		node_print_inorder(cur->right);
	}
	
	return;
}

void bst_print_inorder(bst_t *tree){
// Prints the elements of the tree in sorted order all at the same
// indentation line, one element per line. Makes use of
// node_print_inorder().
	node_t *cur = tree->root;
	node_print_inorder(cur);
	return;
}


void node_write_preorder(node_t *cur, FILE *out, int depth){
	
// Recursive helper function which writes/prints the tree in pre-order
// to the given open file handle. The parameter depth gives how many
// spaces to print before the current node data. Depth increases by 1
// on each recursive call. The function prints the cur node data,
// traverses the left tree, then traverses the right tree.
	int i = 0;
	if(cur == NULL){
		
		return;
	}
	if(out != NULL){
		while(i < depth){
		fprintf(out, "%c", ' ');
		i++;
		}
		fprintf(out, "%s\n", cur->name);
		
		if(cur->left != NULL){
			node_write_preorder(cur->left, out, depth+1);
		}
		if(cur->right != NULL){
			node_write_preorder(cur->right, out, depth+1);
		}
	}
	else{
		while(i < depth){
		printf("%c", ' ');	
		i++;
		}
		
		printf("%s\n", cur->name);
		if(cur->left != NULL){
			node_write_preorder(cur->left, out, depth+1);
		}
		if(cur->right != NULL){
			node_write_preorder(cur->right, out, depth+1);
		}
	}
	
	return;	
}


void bst_save(bst_t *tree, char *fname){
// Saves the tree by opening the named file, writing the tree to it in
// pre-order with node_write_preorder(), then closing the file.
	FILE* ptr = fopen(fname, "w");
	node_t* cur = tree->root;
	node_write_preorder(cur, ptr, 0);
	fclose(ptr);
	return;
}

void bst_print_preorder(bst_t *tree){
// Print all the data in the tree in pre-order with indentation
// corresponding to the depth of the tree. Makes use of
// node_write_preorder() for this.
	node_t* cur = tree->root;
	node_write_preorder(cur, NULL, 0);
	return;
}



int bst_load(bst_t *tree, char *fname ){
// Clears the given tree then loads new elements to it from the
// named. Repeated calls to bst_insert() are used to add strings read
// from the file.  If the tree is stored in pre-order in the file, its
// exact structure will be restored.
	bst_clear(tree);
	FILE* ptr = fopen(fname, "r");
	char name[100];
	while(fscanf(ptr, "%s", name) != EOF){
		bst_insert(tree, name);
		
	}
	fclose(ptr);
	return 0;

}
