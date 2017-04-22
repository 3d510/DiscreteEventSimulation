import java.util.*;
import java.io.*;

//Denna klass ärver Global så att man kan använda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation


public class MainSimulation extends Global{
	
	public static void randomAlgorithm(){
    	Signal actSignal;
    	new SignalList();

    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS Q1 = new QS();
    	Q1.sendTo = null;
    	
    	QS Q2 = new QS();
    	Q2.sendTo = null;
    	
    	QS Q3 = new QS();
    	Q3.sendTo = null;
    	
    	QS Q4 = new QS();
    	Q4.sendTo = null;
    	
    	QS Q5 = new QS();
    	Q5.sendTo = null;

    	Gen Generator = new Gen();
    	Generator.lambda = 9; //Generator ska generera nio kunder per sekund  //Generator shall generate 9 customers per second
    	Generator.sendTo = Q1; //De genererade kunderna ska skickas till kösystemet QS  // The generated customers shall be sent to Q1

    	//To start the simulation the first signals are put in the signal list

    	SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);
    	SignalList.SendSignal(MEASURE, Q2, time);
    	SignalList.SendSignal(MEASURE, Q3, time);
    	SignalList.SendSignal(MEASURE, Q4, time);
    	SignalList.SendSignal(MEASURE, Q5, time);

    	// This is the main loop
    	Random rand = new Random();
    	int arr[]= new int [5];
    	for(int i=0;i<5;i++)
    		arr[i]=0;
    	while (time < 100000){
    		int  n = rand.nextInt(5) + 1; //choose a random queue
    		
    		switch (n){
	    	    case 1: 
	    	    	Generator.sendTo = Q1;
	    	    	arr[0]++;
	    	        break;
	    	    case 2: 
	    	    	Generator.sendTo = Q2;
	    	    	arr[1]++;
	    	        break;
	    	    case 3: 
	    	    	Generator.sendTo = Q3;
	    	    	arr[2]++;
	    	        break;
	    	    case 4: 
	    	    	Generator.sendTo = Q4;
	    	    	arr[3]++;
	    	        break;
	    	    case 5: 
	    	    	Generator.sendTo = Q5;
	    	    	arr[4]++;
	    	        break;    
    		}
    		
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:
    	//Finally the result of the simulation is printed below:
    	/*for(int i=0;i<5;i++)
    		System.out.println(arr[i]);*/
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q1.accumulated/Q1.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q2.accumulated/Q2.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q3.accumulated/Q3.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q4.accumulated/Q4.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q5.accumulated/Q5.noMeasurements);
	}
	
	public static void roundRobinAlgorithm(){
    	Signal actSignal;
    	new SignalList();

    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS Q1 = new QS();
    	Q1.sendTo = null;
    	
    	QS Q2 = new QS();
    	Q2.sendTo = null;
    	
    	QS Q3 = new QS();
    	Q3.sendTo = null;
    	
    	QS Q4 = new QS();
    	Q4.sendTo = null;
    	
    	QS Q5 = new QS();
    	Q5.sendTo = null;

    	Gen Generator = new Gen();
    	Generator.lambda = 9; //Generator ska generera nio kunder per sekund  //Generator shall generate 9 customers per second
    	Generator.sendTo = Q1; //De genererade kunderna ska skickas till kösystemet QS  // The generated customers shall be sent to Q1

    	//To start the simulation the first signals are put in the signal list
    	time = 0;
    	SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);
    	SignalList.SendSignal(MEASURE, Q2, time);
    	SignalList.SendSignal(MEASURE, Q3, time);
    	SignalList.SendSignal(MEASURE, Q4, time);
    	SignalList.SendSignal(MEASURE, Q5, time);

    	// This is the main loop
    	int arr[]= new int [5];
    	for(int i=0;i<5;i++)
    		arr[i]=0;
    	
    	int n=5;
    	while (time < 100000){
    		switch (n){
	    	    case 1: 
	    	    	Generator.sendTo = Q2;
	    	    	n = 2;
	    	        break;
	    	    case 2: 
	    	    	Generator.sendTo = Q3;
	    	    	n = 3;
	    	        break;
	    	    case 3: 
	    	    	Generator.sendTo = Q4;
	    	    	n = 4;
	    	        break;
	    	    case 4: 
	    	    	Generator.sendTo = Q5;
	    	    	n = 5;
	    	        break;
	    	    case 5: 
	    	    	Generator.sendTo = Q1;
	    	    	n = 1;
	    	        break;    
    		}
    		
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Finally the result of the simulation is printed below:
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q1.accumulated/Q1.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q2.accumulated/Q2.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q3.accumulated/Q3.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q4.accumulated/Q4.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q5.accumulated/Q5.noMeasurements);
	}
	
	public static void smallestNumberJobsAlgorithm(){
    	Signal actSignal;
    	new SignalList();

    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS Q1 = new QS();
    	Q1.sendTo = null;
    	
    	QS Q2 = new QS();
    	Q2.sendTo = null;
    	
    	QS Q3 = new QS();
    	Q3.sendTo = null;
    	
    	QS Q4 = new QS();
    	Q4.sendTo = null;
    	
    	QS Q5 = new QS();
    	Q5.sendTo = null;

    	Gen Generator = new Gen();
    	Generator.lambda = 9; //Generator ska generera nio kunder per sekund  //Generator shall generate 9 customers per second
    	Generator.sendTo = Q1; //De genererade kunderna ska skickas till kösystemet QS  // The generated customers shall be sent to Q1

    	//To start the simulation the first signals are put in the signal list
    	time = 0;
    	SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);
    	SignalList.SendSignal(MEASURE, Q2, time);
    	SignalList.SendSignal(MEASURE, Q3, time);
    	SignalList.SendSignal(MEASURE, Q4, time);
    	SignalList.SendSignal(MEASURE, Q5, time);

    	// This is the main loop
    	Random rand = new Random();
    	int arr[]= new int [5];
    	for(int i=0;i<5;i++)
    		arr[i]=0;
    	
    	while (time < 100000){
    		int smallest = 1000000000;
    		int ind = 0;
    		int count = 0;
    		for(int i=0 ;i<5; i++){
    			int queue = 0;
    			switch(i){
    				case 1:
    					queue = Q1.numberInQueue;
    					break;
    				case 2:
    					queue = Q2.numberInQueue;
    					break;
    				case 3:
    					queue = Q3.numberInQueue;
    					break;
    				case 4:
    					queue = Q4.numberInQueue;
    					break;
    				case 5:
    					queue = Q5.numberInQueue;
    					break;
    			}
    			if(queue < smallest){
    				smallest = queue;
    				ind = i;
    				count = 0;
    			} else if(queue == smallest)
					count++;
    		}
    		int  n = -1;
    		if(count != 0){
    			n = rand.nextInt(5) + 1; //choose a random queue
    		}else
    			n = ind;
    		
    		switch (n){
	    	    case 1: 
	    	    	Generator.sendTo = Q1;
	    	    	arr[0]++;
	    	        break;
	    	    case 2: 
	    	    	Generator.sendTo = Q2;
	    	    	arr[1]++;
	    	        break;
	    	    case 3: 
	    	    	Generator.sendTo = Q3;
	    	    	arr[2]++;
	    	        break;
	    	    case 4: 
	    	    	Generator.sendTo = Q4;
	    	    	arr[3]++;
	    	        break;
	    	    case 5: 
	    	    	Generator.sendTo = Q5;
	    	    	arr[4]++;
	    	        break;    
    		}
    		
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}


    	//Finally the result of the simulation is printed below:

    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q1.accumulated/Q1.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q2.accumulated/Q2.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q3.accumulated/Q3.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q4.accumulated/Q4.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q5.accumulated/Q5.noMeasurements);
	}

    public static void main(String[] args) throws IOException {
    	randomAlgorithm();
    	System.out.println("RR:");
    	roundRobinAlgorithm();
    	System.out.println("Smallest jobs:");
    	smallestNumberJobsAlgorithm();
    }
}