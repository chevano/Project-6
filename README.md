# Project-6

Project 6: (In Java) There are four (4) run-length encoding/decoding methods (see below). In this project, you are to implement Method #1 *using the algorithm steps taught in class*. It should be easy to do.

Method #1:  include zero and no wrap-around. 
Method #2:  without zero and no wrap-around.
Method #3:   include zero and wrap-around.
Method #4:   without zero and wrap-around.
**************************************
Language: Java
**************************************
Soft copy due date: 
10/2/2019 Wednesday 2 hours after midnight (10/3/2019, Thursday morning before 2am)
-1 pt due: 10/3/2019 Thursday before midnight
-2 pts due: 10/4/2019 Friday before midnight
- 10 pts after 10/4/2019
Hard copy due date:   10/3/2019 Thursday in class, 
Hard copies for 10/3 and 10/4 late submissions, place under A218 door before 10/6 midnight.
** All projects without hard copy after 10/6/2019 will receive 0 pts even you have submit soft copy on time and even if it works.
**************************************
I. Input (args [0]): a txt file representing an image (gray-scale or binary), where 
	the first text line has 4 integers, representing  the "header" of the input image  	
numRows, numCols, minVal, maxVal, follows by rows and cols of  pixel values (integers).
   
	Example-1 for gray-scale image,
	
5 8 0 9  // The image has 5 rows, 8 columns, min value is 0 and max value is 9
0 0 8 8 8 8 9 9
9 0 7 7 8 8 9 0
0 0 9 9 9 9 9 9
9 9 1 1 8 8 2 2
3 3 1 1 5 5 5 0
   
II. a) Encoded file (NOT from args): to be created during the run-time, to store EncodeMethod1.
The format for the result of an encoded image is given below. 

		5 8 0 9 
		1   	
    		0 0 0 2 	
    		0 2 8 4  	
	   	0 6 9 2  	 
 :
   
        b)  Decoded file (NOT from args):  is created during the run-time to store EncodeMethod1_Decoded.
	// Note: If your program works, the Decode file should be the same as the input file.

*******************************
III. Data structure:  You may add other variables as needed
*******************************
- runLength class
	- numRows (int)
	- numCols (int)
	- minVal (int)
	- maxVal (int)	
	- numRuns (int)
	- whichMethod (int) // for this project it is 1.	
- nameEncodeFile (string)
- nameDecodeFile (string)
	- encodeMethod1 (…) // Given below.
- decodeMethod1 (…) // Use the algorithm steps given in class

*******************************
IV.  main (…)
*******************************
step 0:  inFile ← open args[0], the input image files

 numRows, numCols, minVal, maxVal ← Read from inFile
whichMethod ← 1

step 1:   nameEncodeFile ← args[0] + “_EncodeMethod” + “whichMethod”
	 encodeFile ←open (nameEncodeFile)

step 2: encodeFile ← output numRows, numCols, minVal, maxVal 
	encodeFile ← output whichMethod 

step 3: encodeMethod1 (inFile, encodeFile)  // Algorithm steps is given below. 

Step 4: closed encodeFile 
Step 5: re-open encodeFile 
step 6: nameDecodeFile ← nameEncodeFile + “_Decoded”
Step 7: decodeFile ←open (nameDecodeFile)

step 8: decodeMethod1 (encodeFile, decodeFile)  // On your own.
step 9: close all files

*******************************
V. encodeMethod1 (inFile, encodeFile)  
******************************* 
Step 0: row ← 0

Step 1: col ← 0
	length← 0
	currVal ← read the next pixel (integer) from inFile *one integer at a time*

Step 2: encodeFile ← output row and col and currVal to outFile1
  length ++

Step 3: col++

Step 4: nextVal ← read the next pixel (integer) from inFile

Step 5: if nextVal == currVal
	   	length ++
	  else
	   	encodeFile ← output length
	  	currVal ← nextVal
	  	length ← 1
	   	output row and col and currVal
		 
Step 6: repeat step 3 to step 5 while length < numCols

Step 7: row ++ 
		
Step 8: repeat Step 1 to Step 7 until end of file or while row < numRows
			
step 9: closed all files

**************************************************************
VI decodeMethod1 (encodeFile, decodeFile)  **************************************************************
Use the algorithm steps given in class.
		
