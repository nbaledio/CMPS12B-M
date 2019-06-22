/*Nathan Baledio ID:1574354 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(int argc, char* argv[]){
   FILE* in;  /* file handle for input */  
   FILE* out; /* file handle for output */
   char *word; /* char array to store strings of individual lines from input file */
   char *alpha; /* char array to store alphabetic characters */
   char *number; /* char array to store numeric characters */
   char *punctuation; /* char array to store punctuation characters */
   char *whitespace; /* char array to store whitespace characters */
   int linecounter = 1;
   int alphacounter = 0;
   int numbercounter = 0;
   int punctuationcounter = 0;
   int whitespacecounter = 0;
   word = calloc(256,sizeof(char));
   alpha = calloc(256,sizeof(char));
   number = calloc(256,sizeof(char));
   punctuation = calloc(256,sizeof(char));
   whitespace = calloc(256,sizeof(char));
   
   
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
   
   /* scans through each line of input file. **word is set to characters in current line*/
   while(fgets(word,256,in) != NULL){
	alphacounter = 0;
	numbercounter = 0;
	punctuationcounter = 0;
	whitespacecounter = 0;
	int a  = 0;
	int b = 0;
	int c = 0;
	int d = 0;
	for(int i = 0; i<256; i++){
	   /* checks if character is a number */
	   if((int)word[i] >= 48 && (int)word[i] <= 57){
		numbercounter++;
		number[a] = (char)word[i];
		a++;
	   }else
	   /* checks if character is a letter */
	   if(((int)word[i] >= 65 && (int)word[i] <= 90) || ((int)word[i] >= 97 && (int)word[i] <= 122)){
		alphacounter++;
		alpha[b] = (char)word[i];
		b++;
	   }else
	   /* checks if character is a space */
	   if((int)word[i] == 32 || (int)word[i] == 10){
		whitespacecounter++;
		whitespace[c] = (char)word[i];
		c++;
	   }else
	   /* checks if character is a punctuation if all else fails */
	   if((int)word[i] >= 33 && (int)word[i] <= 126){
		punctuationcounter++;
		punctuation[d] = (char)word[i];
		d++;
	   }
	}
	    	
   
	   fprintf(out,"line %d contains: \n",linecounter);
	   if(alphacounter == 1){fprintf(out,"%d alphabetic character: %s\n",alphacounter,alpha);}else{fprintf(out,"%d alphabetic characters: %s\n",alphacounter,alpha);}
	   if(numbercounter == 1){fprintf(out,"%d numeric character: %s\n",numbercounter,number);}else{fprintf(out,"%d numeric characters: %s\n",numbercounter,number);}
	   if(punctuationcounter ==1){fprintf(out,"%d punctuation character: %s\n",punctuationcounter,punctuation);}else{fprintf(out,"%d punctuation characters: %s\n",punctuationcounter,punctuation);}
	   if(whitespacecounter == 1){fprintf(out,"%d whitespace character: %s\n",whitespacecounter,whitespace);}else{fprintf(out,"%d whitespace characters: %s\n",whitespacecounter,whitespace);}
	   linecounter++;
           memset(word,0,256);
	   memset(alpha,0,256);
	   memset(number,0,256);
	   memset(whitespace,0,256);
	   memset(punctuation,0,256);
   }
   /*Frees memory allocation*/
   free(word);
   free(alpha);
   free(number);
   free(punctuation);
   free(whitespace);
   
   /* close input and output files */
   fclose(in);
   fclose(out);
}
