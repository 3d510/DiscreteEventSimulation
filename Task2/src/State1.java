import java.util.*;
import java.io.*;
import java.lang.*;

class State1 extends State0 {

    public double generateDelayTime() {
        return exp(delayTime);
    }

}