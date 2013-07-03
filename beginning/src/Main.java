import java.util.zip.GZIPInputStream;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	public static void main(String[] arg){
		
		ErrorLogger logger=new ErrorLogger();
		Scanner inputScanner=null;
		
		
		
		try {
			InputStream fileStream = new FileInputStream(arg[0]);
			InputStream gzipStream = new GZIPInputStream(fileStream);
			inputScanner=new Scanner (gzipStream);
		}

		
		
		catch (IOException io){
			System.out.println("IOException");
			System.exit(0);
		}

		
		
		
		
		HashMap<String, ObjectEventRecord> hash=new HashMap();
		long startOfProcess=System.currentTimeMillis();
		int numberOfIllegalAccesses=0;
		
		while (inputScanner.hasNextLine()){

			String nextLine=inputScanner.nextLine();
			
			
			
			System.out.println(nextLine+" nextline");
			
			
			Event event=new Event(nextLine);

			
			
			//free and status "A"
			if  (hash.get(event.id)==null && event.status.equalsIgnoreCase("A")){
				
				ObjectEventRecord record=new ObjectEventRecord(event);
				hash.put(event.id, record);
				System.out.println("Object " + event.id +" added to the records for the first time");
				System.out.println();
				System.out.println();
			}
			
			
			
			
			//free but not status "A"
			else if (hash.get(event.id)==null && !event.status.equalsIgnoreCase("A")){

				Error notBornError=new Error("The object is not born so you cannot update it!");
				logger.getLogger().log(Level.SEVERE,"The object with id "+event.id+" is not born so you cannot update it!");
				numberOfIllegalAccesses++;
				
				System.out.println();
				System.out.println();
			}
			
			
			
			
			
			//occupied and alive
			else if (hash.get(event.id)!=null && hash.get(event.id).isAlive()){
				
				ObjectEventRecord record=hash.get(event.id);
				record.updateRecord(event);
				hash.put(event.id, record);
				
				System.out.println("Event " + event.id +" was updated in the records");
				
				System.out.println();
				System.out.println();
					
					
			}
			
			
			
			
			//occupied but dead
			else if (hash.get(event.id)!=null && !hash.get(event.id).isAlive()){				
				
				Error DeadError=new Error("The object is dead so you cannot update it!");
				logger.getLogger().log(Level.SEVERE,"The object with id "+event.id+" is dead so you cannot update it!");
				numberOfIllegalAccesses++;
				
				System.out.println();
				System.out.println();
			}
	
	}
		long endOfProcess=System.currentTimeMillis();
		long timeTakenInMillisecs=endOfProcess-startOfProcess;
		int seconds = (int) (timeTakenInMillisecs / 1000) % 60 ;
		int minutes = (int) ((timeTakenInMillisecs / (1000*60)) % 60);
		int hours   = (int) ((timeTakenInMillisecs / (1000*60*60)) % 24);
		System.out.printf("It took %d hours %d minutes %d seconds to process 11MB file\n",hours,minutes,seconds);
		System.out.printf("Number of recorded errors, i.e. trying to access not initialised or dead object %d \n",numberOfIllegalAccesses);
	}
		
	
		
		
	
	
	
	
	
	
		public static boolean testing(String path){
			boolean output=true;
			Scanner inputScanner=null;
			try {
				InputStream fileStream = new FileInputStream(path);
				InputStream gzipStream = new GZIPInputStream(fileStream);
				inputScanner=new Scanner (gzipStream);
			}

			catch (IOException e){
				System.out.println("Cannot open the file ");
				return false;
			} 

			HashMap<String, ObjectEventRecord> hash=new HashMap<String, ObjectEventRecord >();
			while (inputScanner.hasNextLine()){
				
				String nextLine=inputScanner.nextLine();
				System.out.println(nextLine+" nextline");
				Event event=new Event(nextLine);

				
				
				//free and status "A"
				if  (hash.get(event.id)==null && event.status.equalsIgnoreCase("A")){
					
					ObjectEventRecord record=new ObjectEventRecord(event);
					hash.put(event.id, record);
				}
				
				
				//free but not status "A"
				else if (hash.get(event.id)==null && !event.status.equalsIgnoreCase("A")){
					System.out.println("Error1: object not born");
					return false;
				}
				
				
				
				//occupied and alive
				else if (hash.get(event.id)!=null && hash.get(event.id).isAlive()){
					hash.get(event.id).updateRecord(event);
				}
				
				
				//occupied but dead
				else if (hash.get(event.id)!=null && !hash.get(event.id).isAlive()){
					System.out.println ("error2: dead object");
					return false;
				}

				
				
				
			
			
		}

			return output;
			
		}
			

}

