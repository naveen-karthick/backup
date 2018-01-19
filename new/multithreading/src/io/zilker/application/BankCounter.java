package io.zilker.application;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Logger;
public class BankCounter {

	static Logger log=Logger.getLogger(BankCounter.class.getName());
	static Scanner input=new Scanner(System.in);
	
	static public class counter implements Runnable
	
	{
		int timeOfTransaction=0;
		int requestId=0;
		
		public counter(int timeOfTransaction,int requestId)
		{
			this.timeOfTransaction=timeOfTransaction;
			this.requestId=requestId;
		}
		
		
		@Override
		
		public void run() {
			String name=Thread.currentThread().toString().substring(21, 22);
			
			log.info("Counter "+name+" is processing Request Id "+requestId);
			try {
				Thread.sleep(timeOfTransaction);
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("Counter "+name+" Completed RequestId "+requestId);
			
		}
		
	}
	
	public static void main(String[] args)
	{
		
		log.info("Enter the number of Bank counters available");		
		int noOfCounters=input.nextInt();
		log.info("Enter the number of transactions to be processed");
		int noOfRequests=input.nextInt();
		log.info("Enter the transaction time of each transaction in seconds");
		int[] transactionTime=new int[noOfRequests];
		for(int j=0;j<noOfRequests;j++)
		{
			transactionTime[j]=1000*input.nextInt();
		}
		
		ExecutorService executor=Executors.newFixedThreadPool(noOfCounters);			
		
		for(int j=1;j<=noOfRequests;j++)
		{
			Runnable count =new counter(transactionTime[j-1],j);
			executor.execute(count);
			
			
			
			
		}
		executor.shutdown();
		while(!executor.isTerminated())
		{
			
		}
		log.info("Processed all Requests");
		
		
	}
}
