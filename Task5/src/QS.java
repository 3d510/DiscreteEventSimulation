import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {

	public int numberInQueue = 0, curJobsInQueueSum = 0, noMeasurements = 0;
	int queueId;
	Random slump = new Random();
	List<Double> enterTime = new ArrayList<Double>();
	List<Double> leaveTime = new ArrayList<Double>();

	public void TreatSignal(Signal x){
		switch (x.signalType) {

			case ARRIVAL:
				numberInQueue++;
				enterTime.add(time);
				if (numberInQueue == 1){
					SignalList.SendSignal(READY,this, time + exp(0.5, slump), queueId);
				}
			 	break;

			case READY:
				numberInQueue--;
				leaveTime.add(time);
				if (numberInQueue > 0){
					SignalList.SendSignal(READY, this, time + exp(0.5, slump), queueId);
				}
				break;

			case MEASURE:
				noMeasurements++;
				curJobsInQueueSum = curJobsInQueueSum + numberInQueue;
				SignalList.SendSignal(MEASURE, this, time + uniform(1, slump), queueId);
				break;
		}
	}

	// return two values: number of jobs entering this queue and their total time in system
	public List<Double> calTimeInSystem() {
		List<Double> ret = new ArrayList<Double>();
		double totalTimeInSystem = 0;
		for (int i = 0; i < leaveTime.size(); i++) {
			totalTimeInSystem += leaveTime.get(i) - enterTime.get(i);
		}
		ret.add(leaveTime.size()*1.0);
		ret.add(totalTimeInSystem);
		return ret;
	}
}