#Makefile for queueClient.c

queueClient : queueClient.o queue.o
	gcc -o queueClient queueClient.o queue.o
	
queueClient.o : queueClient.c queue.h
	gcc -c queueClient.c
	
queue.o : queue.c queue.h
	gcc -c queue.c
	
check:
	valgrind --leak-check=full queueClient queueClient.c out.txt

clean :
	rm -f queueClient queueClient.o queue.o
