import java.io.*;
import java.util.List;

//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

		double[] interArrTimeArr = {0.12, 0.11, 0.15, 2};
		String[] messages = {"\tRandom selection algorithm:\n",
							"\tRound Robin selection algorithm:\n",
							"\tSmallest selection algorithm\n "
		};
		SelectionAlgorithm[] scheme = new SelectionAlgorithm[3];
		scheme[0] = new RandomSelection();
		scheme[1] = new RoundRobinSelection();
		scheme[2] = new RSmallestSelection();

		for (int i = 0; i < interArrTimeArr.length; i++) {
			System.out.printf("Mean interarrival time = %f - lambda = %f\n", interArrTimeArr[i], 1/interArrTimeArr[i]);
			for (int j = 0; j < scheme.length; j++) {
				System.out.print(messages[j]);
				//// intialize ////
				time = 0;
				Gen generator = new Gen(interArrTimeArr[i]);
				QS[] queues = new QS[5];

				for (int k = 0; k < queues.length; k++) {
					queues[k] = new QS();
					queues[k].queueId = k;
				}
				SelectionAlgorithm sa = scheme[j];
				sa.qs = queues;


				//// simulate ////
				Signal actSignal;
				new SignalList();

				SignalList.SendSignal(READY, generator, time, -1);
				for (int k = 0; k < queues.length; k++) {
					SignalList.SendSignal(MEASURE, queues[k], time, k);
				}
				while (time < 100000) {
					actSignal = SignalList.FetchSignal();
					time = actSignal.arrivalTime;
					Proc dest = actSignal.destination;
					if (actSignal.queueNo == -1) {// READY signal for generator
						generator.sendToId = sa.selectQueue();
						generator.sendTo = queues[generator.sendToId];
					}
					dest.TreatSignal(actSignal);
				}

				//// calculate results ////
				// mean time in system
				double totalTimeInSystem = 0, totalJobs = 0;
				for (int k = 0; k < queues.length; k++) {
					List<Double> tmp = queues[k].calTimeInSystem();
					totalJobs += tmp.get(0);
					totalTimeInSystem += tmp.get(1);
				}
				System.out.printf("\t\tMean time of a job in the system T = %f\n", totalTimeInSystem/totalJobs);
				// mean number in queues
				double jobsInQueuesSum = 0;
				for (int k = 0; k < queues.length; k++) {
					jobsInQueuesSum += queues[k].curJobsInQueueSum;
				}
				System.out.printf("\t\tMean number of jobs in queues system N = %f\n", jobsInQueuesSum/queues[0].noMeasurements);
				// Little's theorem
				System.out.printf("\t\tUsing Little's theorem: N = lambda * T = %f\n", totalTimeInSystem/(totalJobs * interArrTimeArr[i]));
			}
			System.out.println();
		}
    }
}