#include<stdio.h>
#include<stdlib.h>
#include "queue.h"

int main(int argc, char* argv[]){
   FILE* in;  /* file handle for input */  
   FILE* out; /* file handle for output */
   char *word;
   word = calloc(256,sizeof(char));

   /* check command line for correct number of arguments */
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   /* open input file for reading */
   in = fopen(argv[1], "r");
   if( in==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   /* open output file for writing */
   out = fopen(argv[2], "w");
   if( out==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   
	// Creates an empty queue to be used
	Node queue;
	Node *head1;
	head1 = (Node *)malloc(sizeof(Node));
	head1->number = -1;
	queue = *head1;
	Node *head = &queue;

   // Runs through input file's commands
   while(fgets(word,256,in) != NULL){
	if((int)word[0] == 100){
		dequeue(head,out);	
	}
	if((int)word[0] == 101){
		char *dummy;
		dummy = calloc(256,sizeof(char));
		int x;
		sscanf(word,"%s %d",dummy,&x);
		enqueue(x,head,out);
		free(dummy);
	}
	if((int)word[0] == 112){
		printqueue(head,out);
	}
   }

   free(word);
   free(head1);
   /* close input and output files */
   fclose(in);
   fclose(out);

   return(EXIT_SUCCESS);
}
