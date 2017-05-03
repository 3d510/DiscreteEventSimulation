import java.util.Random;

public class RandomSelection extends SelectionAlgorithm{

    public int selectQueue() {
        Random rand = new Random();
        return rand.nextInt(numberOfQueues);
    }
}
