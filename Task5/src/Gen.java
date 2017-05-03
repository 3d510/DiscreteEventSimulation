import java.util.*;
import java.io.*;

//It inherits Proc so that we can use time and the signal names without dot notation

class Gen extends Proc{

	//The random number generator is started:
	Random slump = new Random();
	double interArrTime;
	int sendToId;
	Proc sendTo;

	public Gen(double interArrTime) {
		this.interArrTime = interArrTime;
	}

	//What to do when a signal arrives
	public void TreatSignal(Signal x){
		switch (x.signalType){
			case READY:
				SignalList.SendSignal(ARRIVAL, sendTo, time, sendToId);
				SignalList.SendSignal(READY, this, time + uniform(interArrTime, slump), -1);
				break;
		}
	}
}