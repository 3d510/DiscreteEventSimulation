import java.util.*;
import java.io.*;
import java.lang.*;

class State0 extends StateBase {

    public void arriveQA() {
        numberInQA++;
        if (numberInQA == 1 && numberInQB == 0)
            insertEvent(DELAY, time + jobAServiceTime);
        insertEvent(ARRIVEQA, time + exp(interArriveTime));
    }

    public void delay() {
        if (numberInQA == 0)
            return;
        numberInQA--;
        insertEvent(ARRIVEQB, time + generateDelayTime());
        if (numberInQA > 0 && numberInQB == 0)
            insertEvent(DELAY, time + jobAServiceTime);
    }

    public void arriveQB() {
        numberInQB++;
        if (numberInQB == 1)
            insertEvent(DEPART, time + jobBServiceTime);
    }

    public void depart() {
        numberInQB--;
        if (numberInQB > 0)
            insertEvent(DEPART, time + jobBServiceTime);
        if (numberInQA > 0 && numberInQB == 0)
            insertEvent(DELAY, time + jobAServiceTime);
    }

}