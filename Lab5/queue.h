//-----------------------------------------------------------------------------
// queue.h
// Header file for the queue ADT
//-----------------------------------------------------------------------------

#ifndef queue_H_INCLUDE_
#define queue_H_INCLUDE_

#include<stdio.h>

typedef struct Node{
	int number;
	struct Node *next;
}Node;

//Creates a new queue
Node newqueue(void);

//Frees queue memory allocation
void freequeue(Node * head);

//Prints queue to output file
void printqueue(Node * head,FILE* output);

//Enqueues a number to end of queue
void enqueue(int n,Node * head,FILE* output);

//Dequeues number at beginning of queue
void dequeue(Node * head,FILE* output);

#endif

