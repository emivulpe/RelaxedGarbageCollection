import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class ErrorLogger {
	 private final static Logger logger = Logger.getLogger(ErrorLogger.class.getName());
	 private static FileHandler fh = null;
	 
	 
	 public ErrorLogger(){
		 try { 
		 fh=new FileHandler("logger.log"); 
		 } 
		 
		 
		 catch (SecurityException | IOException e) {	 
		 e.printStackTrace();
		 }
		 
		  
		 Logger l = Logger.getLogger("");
		 fh.setFormatter(new SimpleFormatter());
		 l.addHandler(fh);
		 l.setLevel(Level.SEVERE);
		 
	 }
	 
	 
	 public Logger getLogger(){
		 return logger;
	 }
	 
	 
	 
}
