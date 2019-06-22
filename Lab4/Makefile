# Makefile for charType.c

charType : charType.o
	gcc -o charType charType.o

charType.o : charType.c
	gcc -c charType.c
	
check:
	valgrind --leak-check=full charType charType.c out.txt

clean :
	rm -f charType charType.o
