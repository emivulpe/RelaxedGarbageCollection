import java.util.zip.GZIPInputStream;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Main {
	
	public static void main(String[] arg){
		
		InputStream inputStream=System.in;
		String input=inputStream+"";
		Scanner inputScanner=null;
		try {
			InputStream fileStream = new FileInputStream("C:/Users/Emi/Desktop/traces/normal.tar.gz");
			//InputStream gzipStream = new GZIPInputStream(fileStream);
			inputScanner=new Scanner (fileStream);
		}
		catch (FileNotFoundException e){
			System.out.println("Cannot open the file "+input);
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, Stack<Event>> hash=new HashMap<String, Stack<Event> >();
		//int count=0;
		while (inputScanner.hasNextLine()){
			//count++;
			String nextLine=inputScanner.nextLine();
			System.out.println(nextLine+"nextline");
			Scanner lineScanner=new Scanner(nextLine);
			Event event=new Event(lineScanner);
			System.out.println(event.status+event.id);
//			System.out.println(event.id);
//			System.out.println(hash.get(event.id)+"Opkn");
//			System.out.println(hash.get(event.id)==null+"null12");
//			System.out.println(event.status.equalsIgnoreCase("A")+"=A");
			//System.out.println(event.status+"status");
			
			
			//free and status "A"
			if  (hash.get(event.id)==null && event.status.equalsIgnoreCase("A")){
				
				Stack s=new Stack<Event>();
				//s.push("i");
				System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooo");
				s.push(event);
				hash.put(event.id, s);
				//System.out.println(hash.get(event.id)+"Wqds");
			}
			
			
			//free but not status "A"
			else if (hash.get(event.id)==null && !event.status.equalsIgnoreCase("A")){
				System.out.println("Error1");
			}
			
			
			
			//occupied and alive
			else if (!hash.get(event.id).isEmpty() && !hash.get(event.id).peek().status.equalsIgnoreCase("D")){
				hash.get(event.id).push(event);
					
					
				//}
			}
			
			
			//occupied but dead
			else if (!hash.get(event.id).isEmpty() && hash.get(event.id).peek().status.equalsIgnoreCase("D")){
				System.out.println ("error2");
			}
			//else if (event.status.equalsIgnoreCase("U")&&)
			System.out.println(hash.get(event.id)+event.id);
			
			
			
		
		
	}
		System.out.println(hash+"pppppppppppppppppppppppppp");
		
	}
		
		
		public static boolean testing(String path){
			boolean output=true;
			Scanner inputScanner=null;
			try {
				InputStream fileStream = new FileInputStream(path);
				InputStream gzipStream = new GZIPInputStream(fileStream);
				inputScanner=new Scanner (gzipStream);
			}
			catch (FileNotFoundException e){
				System.out.println("Cannot open the file ");
				System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<String, Stack<Event>> hash=new HashMap<String, Stack<Event> >();
			//int count=0;
	
			while (inputScanner.hasNextLine()&&output){
				//count++;
				String nextLine=inputScanner.nextLine();
				System.out.println(nextLine+"nextline");
				Scanner lineScanner=new Scanner(nextLine);
				
				Event event=new Event(lineScanner);
				System.out.println(event.status+event.id);
//				System.out.println(event.id);
//				System.out.println(hash.get(event.id)+"Opkn");
//				System.out.println(hash.get(event.id)==null+"null12");
//				System.out.println(event.status.equalsIgnoreCase("A")+"=A");
				//System.out.println(event.status+"status");
				
				
				//free and status "A"
				if  (hash.get(event.id)==null && event.status.equalsIgnoreCase("A")){
					
					Stack s=new Stack<Event>();
					//s.push("i");
					System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooo");
					s.push(event);
					hash.put(event.id, s);
					//System.out.println(hash.get(event.id)+"Wqds");
				}
				
				
				//free but not status "A"
				else if (hash.get(event.id)==null && !event.status.equalsIgnoreCase("A")){
					System.out.println("Error1");
					output=false;
				}
				
				
				
				//occupied and alive
				else if (!hash.get(event.id).isEmpty() && !hash.get(event.id).peek().status.equalsIgnoreCase("D")){
					hash.get(event.id).push(event);
						
						
					//}
				}
				
				
				//occupied but dead
				else if (!hash.get(event.id).isEmpty() && hash.get(event.id).peek().status.equalsIgnoreCase("D")){
					System.out.println ("error2");
					output=false;
				}
				//else if (event.status.equalsIgnoreCase("U")&&)
				System.out.println(hash.get(event.id)+event.id);
				
				
				
				
			
			
		}
			return output;
			
		}

}

