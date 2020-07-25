package BehavioralPatterns;


import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import StructuralPatterns.ProxyPatternClient;  
  
public class StrategyPatternDemo {  
	static PrintStream myout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	private static final Logger logger = LogManager.getLogger(ProxyPatternClient.class);
	static Properties props = new Properties();
    public static void main(String[] args) throws NumberFormatException, IOException {  
    	  BasicConfigurator.configure();
    	  Scanner scan=new Scanner(System.in);
  			
  		float value1;
		float value2 ;
  		while(true) {
  		
  			//l23ogger.warn("Please enter two values(in float) ");
  			
  			try {
  				System.out.println("Enter the first number");
  				String a1=scan.next();
  				 value1=Float.parseFloat(a1);
  				System.out.println("Enter the second number");
  				String b1=scan.next();
  				
  				 value2=Float.parseFloat(b1);
  			}
  			catch(Exception e) {
  				System.out.println("please enter valid numbers");
  				continue;
  			}
  			 BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
  	         /* logger.warn("Please enter two values(in float)");
  	          myout.print("Enter the first value: ");  
  	          float value1=Float.parseFloat(br.readLine());  
  	          myout.print("Enter the second value: ");  
  	          float value2=Float.parseFloat(br.readLine()); */
  	          //logger.info("Addition class started....");
  	          Context context = new Context(new Addition());          
  	          myout.println("Addition = " + context.executeStrategy(value1, value2));  
  	         // logger.info("Subtraction class started");
  	          context = new Context(new Subtraction());       
  	          myout.println("Subtraction = " + context.executeStrategy(value1, value2));  
  	         // logger.info("Multiplication class started");
  	          context = new Context(new Multiplication());        
  	          myout.println("Multiplication = " + context.executeStrategy(value1, value2));
  	         // logger.info("Modular division class started");
	          context = new Context(new Division());        
	          myout.println("Modular division = " + context.executeStrategy(value1, value2));
	          context = new Context(new I_Division());        
	          myout.println("Division = " + context.executeStrategy(value1, value2));
  			
  			return;
  					
  		}
  		}
  		
  		
  		
  		
  		
         
       }  
  
