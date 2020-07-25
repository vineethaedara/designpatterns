package CreationalPatterns;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.TimeUnit;  
import java.util.concurrent.atomic.AtomicLong; 
import java.io.PrintStream;
import java.util.Properties;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ObjectPoolDemo{  
	  static PrintStream myout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	  private static final Logger logger = LogManager.getLogger(ObjectPoolDemo.class);
	  static Properties props = new Properties();
      private ObjectPool<ExportingProcess> pool;  
      private AtomicLong processNo=new AtomicLong(0);  
      public void setUp() {  
            
      pool = new ObjectPool<ExportingProcess>(4, 10, 5)  
        {  
            protected ExportingProcess createObject()  
            {   
                return new ExportingProcess( processNo.incrementAndGet());  
            }  
        };  
    }  
    public void tearDown() {  
        pool.shutdown();  
    }  
    public void testObjectPool() {  
        ExecutorService executor = Executors.newFixedThreadPool(8);   
        logger.info("Executing 8 tasks in separate threads");
        executor.execute(new ExportingTask(pool, 1));  
        executor.execute(new ExportingTask(pool, 2));  
        executor.execute(new ExportingTask(pool, 3));  
        executor.execute(new ExportingTask(pool, 4));  
        executor.execute(new ExportingTask(pool, 5));  
        executor.execute(new ExportingTask(pool, 6));  
        executor.execute(new ExportingTask(pool, 7));  
        executor.execute(new ExportingTask(pool, 8));  
  
        executor.shutdown();  
        try {  
            executor.awaitTermination(30, TimeUnit.SECONDS);  
            } catch (InterruptedException e)  
              
              {  
               logger.error(e);	
               e.printStackTrace();  
              }  
    }  
    public static void main(String args[])  {   
    	BasicConfigurator.configure();
    	logger.debug("Debugging started in ObjectPoolDemo");
        ObjectPoolDemo op=new ObjectPoolDemo();  
        op.setUp();  
        op.tearDown();  
        op.testObjectPool();  
   }   
}  