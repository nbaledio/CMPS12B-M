#include<stdio.h>
#include<stdlib.h>
#include "queue.h"

//Creates a new queue by creating a head Node
Node newqueue(void){
	Node *head;
	head = (Node *)malloc(sizeof(Node));
	head->number = -1;
	return *head;	
}

//Frees queue memory allocation
void freequeue(Node * head){
	free(head);
}

//Prints queue to output file
void printqueue(Node * head,FILE* output){
	if(head->number == -1){
		fprintf(output,"\n");
		return;
	}
	Node *current = head;
	while(current != NULL){
		if(current->next == NULL){
			fprintf(output,"%d",current->number);
			break;
		}
		fprintf(output,"%d ",current->number);
		current = current->next;
	}
	fprintf(output,"\n");
}

//Enqueues a number to end of queue
void enqueue(int n, Node * head,FILE* output){
	if(head->number == -1){
		head->number = n;
		fprintf(output,"enqueued %d\n", n);
		return;
	}
	Node *current = head;
	while(current->next != NULL){
		current = current->next;	
	}
	current->next = malloc(sizeof(Node));
	current->next->number = n;
	current->next->next = NULL;
	fprintf(output,"enqueued %d\n", n);
}


//Dequeues number at beginning of queue
void dequeue(Node * head,FILE* output){
	if(head->next == NULL){
		if(head->number == -1){
			fprintf(output,"empty\n");
			return;
		}
		fprintf(output,"%d\n",head->number);

		head->number = -1;
		return;
	}
	Node holder;
	holder.number = head->next->number;
	holder.next = head->next->next;
	fprintf(output,"%d\n", head->number);
	head->number = holder.number;
	head->next = holder.next;
}
