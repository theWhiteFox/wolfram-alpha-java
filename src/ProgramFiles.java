package wolfRam;

import java.io.*;

public class ProgramFiles {
	// create class vars 
	private String fileName;
	private static boolean fileCreated;
	
	// create constructors
	// create constructors
		public  ProgramFiles (String filename)throws FileNotFoundException, IOException{
			this.fileName = filename;
			// creating a new file 
			File newFiles = new File (filename);
			//flow controol
			// If file created ==   true print created else the file can not be created becauase of file in the location.
			if (newFiles.createNewFile()){
				fileCreated = true;
			}else{
				fileCreated = false;
			}

		}
	
	// create getters and setters (If necessary)
	public String getFileName(){
		// objectname.getFileName();  in calling class. 
		return fileName;
	}
	public Boolean getFileCreated(){
		return fileCreated;
	}
	
	// BEHAVIORS
	
	// write to file 
	public void writeTofile(String strings, ProgramFiles fileObject){
		try {
			PrintWriter writeQuestionToFile = new PrintWriter(new BufferedWriter(new FileWriter (fileObject.getFileName(),true)));
			// write user Question to buffer and place cursor on new line
			writeQuestionToFile.append( strings + "\n");	
			// this will now send the text to the file
			writeQuestionToFile.close();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}// end of class