import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RSmallestSelection extends SelectionAlgorithm{

    public int selectQueue() {
        List<Integer> smallest = new ArrayList<>();
        Random rand = new Random();
        int min = 1000000000;
        for (int i = 0; i < 5; i++) {
            if (min > qs[i].numberInQueue)
                min = qs[i].numberInQueue;
        }
        for (int i = 0; i < 5; i++) {
            if (qs[i].numberInQueue == min)
                smallest.add(i);
        }
        return smallest.get(rand.nextInt(smallest.size()));
    }
}
