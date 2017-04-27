import java.util.*;
import java.io.*;
import java.lang.*;

class State2 extends StateBase {

    public void arriveQA() {
        numberInQA++;
        if (numberInQA == 1)
            insertEvent(DELAY, time + jobAServiceTime);
        insertEvent(ARRIVEQA, time + exp(interArriveTime));
    }

    public void delay() {
        numberInQA--;
        insertEvent(ARRIVEQB, time + generateDelayTime());
        if (numberInQA > 0)
            insertEvent(DELAY, time + jobAServiceTime);
        if (numberInQB > 0 && numberInQA == 0)
            insertEvent(DEPART, time + jobBServiceTime);
    }

    public void arriveQB() {
        numberInQB++;
        if (numberInQB == 1 && numberInQA == 0)
            insertEvent(DEPART, time + jobBServiceTime);
    }

    public void depart() {
        if (numberInQB == 0)
            return;
        numberInQB--;
        if (numberInQB > 0 && numberInQA == 0)
            insertEvent(DEPART, time + jobBServiceTime);
    }
}