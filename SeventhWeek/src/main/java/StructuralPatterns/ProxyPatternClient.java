package StructuralPatterns;

import java.io.PrintStream;
import java.util.Properties;
import java.io.FileDescriptor;
import java.io.FileOutputStream;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

 
public class ProxyPatternClient {  

	static PrintStream myout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	private static final Logger logger = LogManager.getLogger(ProxyPatternClient.class);
	static Properties props = new Properties();
    public static void main(String[] args)   
    {  
    	BasicConfigurator.configure();
    	logger.debug("Debugging started in ProxyPatternClient");
        OfficeInternetAccess access = new ProxyInternetAccess("Bhargavi Mokharala"); 
        access.grantInternetAccess();  
    }  
}  