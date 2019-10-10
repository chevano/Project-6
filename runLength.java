import java.util.*;
import java.io.*;

public class runLength{
	 public static int numRows;
	 public static int numCols;
	 public static int minVal;
	 public static int maxVal;
	 public static int numRuns;
	 public static int whichMethod;
	 public static String nameEncodeFile;
	 public static String nameDecodeFile;

	runLength(){
		numRows	= 0;
		numCols	= 0;
		minVal	= 0;
		maxVal	= 0;
		numRuns	= 0;
	}
	
	runLength(int numRows, int numCols, int minVal, int maxVal, int numRuns){
		this.numRows	= numRows;
		this.numCols	= numCols;
		this.minVal	= minVal;
		this.maxVal	= maxVal;
		this.numRuns	= numRuns;
	}

	public static void whichMethod(int num){
		whichMethod = num;
	}

	public static void nameEncodeFile(String encodeFilename){
		nameEncodeFile = encodeFilename;
	}

	public static void nameDecodeFile(String decodeFilename){
		nameDecodeFile = decodeFilename;
	}

	public static void encodeMethod1(Scanner inFile,BufferedWriter encodeFile) throws IOException  {
		int row, col, length, nextVal, currVal = -1; 
		row = col = length = 0;

		// append at the end of the file
		encodeFile = new BufferedWriter(new FileWriter(nameEncodeFile,true)); 
					
		for(int start_row = 0; start_row < numRows; start_row++){
			for(int start_col = 0; start_col < numCols; start_col++){
				nextVal = Integer.parseInt(inFile.next());
				if(currVal == -1)
					currVal = nextVal;

				// Case #1: Same Grey Scale Value
				if(currVal == nextVal)
					length++;

				// Case #2: Different Grey Scale Value
				else{
					++numRuns;
					encodeFile.write(row + "\t" + col + "\t" + currVal 
							+ "\t" + (length) + "\n");
					encodeFile.flush();
    
					currVal = nextVal;
					length = 1;
					row = start_row;
					col = start_col;
				}
				// No wrapped-around
				if(col == (numCols - 1)){	
					//Renewing
					encodeFile.write(row + "\t" + col + "\t" + currVal
							 + "\t" + (length) + "\n");
					encodeFile.flush();

					row = start_row + 1;
					col = 0;
					length = 0;
					currVal = -1;
				}
			}		
		}
		numRuns++;
		encodeFile.close();	
	}

	public static void decodeMethod1(Scanner encodeFile,BufferedWriter decodeFile)throws IOException{
		int start_row, start_col, grey_scale, length;		
		runLength RL = new runLength(	Integer.parseInt(encodeFile.next()),
						Integer.parseInt(encodeFile.next()),
						Integer.parseInt(encodeFile.next()),
						Integer.parseInt(encodeFile.next()),
						0
					    );// extracting the header content

		start_row   = Integer.parseInt(encodeFile.next());
		start_col   = Integer.parseInt(encodeFile.next());
		whichMethod = Integer.parseInt(encodeFile.next());
		grey_scale  = Integer.parseInt(encodeFile.next());
		length	    = Integer.parseInt(encodeFile.next());
		
		// append at the end of the file
		decodeFile = new BufferedWriter(new FileWriter(nameDecodeFile,true)); 
		decodeFile.write(numRows + " " + numCols + " " + minVal + " " + maxVal + "\n");
		decodeFile.flush();

		int [][]arrayn = new int[numRows][numCols];

		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				if(length <= 0){
					start_row   = Integer.parseInt(encodeFile.next());
					start_col   = Integer.parseInt(encodeFile.next());
					grey_scale  = Integer.parseInt(encodeFile.next());
					length	    = Integer.parseInt(encodeFile.next());
				}
				length--;
				if(col == (numCols-1)){
					arrayn[row][col] = grey_scale;
					decodeFile.write(arrayn[row][col] + "\n");
					decodeFile.flush();
				}
				else{
					arrayn[row][col] = grey_scale;
					decodeFile.write(arrayn[row][col]+ " ");
					decodeFile.flush();
				}			
			}
		}		
	}

	public static void main(String[] args){
		try{
			Scanner inFile = new Scanner(new FileReader(args[0]));
			runLength RL = new runLength(	Integer.parseInt(inFile.next()),
							Integer.parseInt(inFile.next()),
							Integer.parseInt(inFile.next()),
							Integer.parseInt(inFile.next()),
							0
						    );
			whichMethod(1);
			
			nameEncodeFile(args[0] + "_EncodeMethod" + whichMethod);
			BufferedWriter encodeFile = new BufferedWriter(new FileWriter(nameEncodeFile));

			String header = "";
			header += numRows + "\t" + numCols + "\t" + minVal;
			header += "\t" + maxVal + "\r\n";

			encodeFile.write(header);
			encodeFile.write(whichMethod + "\n");
			encodeFile.close();

			encodeMethod1(inFile,encodeFile);

			encodeFile.close();
			
			Scanner encode_File = new Scanner(new FileReader(nameEncodeFile));

			nameDecodeFile(nameEncodeFile + "_Decoded");

			BufferedWriter decodeFile = new BufferedWriter(new FileWriter(nameDecodeFile));

			decodeMethod1(encode_File,decodeFile);

			encodeFile.close();
			decodeFile.close();
			inFile.close();
		}
		catch(IOException| IllegalArgumentException | NoSuchElementException e){} 
	}
}