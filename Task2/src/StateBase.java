import java.util.Random;

public abstract class StateBase extends GlobalSimulation {

    public int numberInQA = 0, numberInQB = 0, accumulated = 0, noMeasurements = 0;
    Random slump = new Random();

    public void treatEvent(Event x) {
//		x.show();
        switch (x.eventType){
            case ARRIVEQA:
                arriveQA();
                break;
            case DELAY: //sending job A to be B
                delay();
                break;
            case ARRIVEQB:
                arriveQB();
                break;
            case DEPART:
                depart();
                break;
            case MEASURE:
                measure();
                break;
        }
    }

    public abstract void arriveQA();
    public abstract void arriveQB();
    public abstract void delay();
    public abstract void depart();

    private void measure(){
        int numberInQueue = numberInQA + numberInQB;
        accumulated = accumulated + numberInQueue;
        noMeasurements++;
        insertEvent(MEASURE, time + measureTime);
    }

    public double exp(double mean) {
        return -Math.log(1-slump.nextDouble())*mean;
    }

    // this will be overwritten in question b when delay time is exp distributed
    public double generateDelayTime() {
        return delayTime;
    }

}
