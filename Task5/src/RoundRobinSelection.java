public class RoundRobinSelection extends SelectionAlgorithm{

    int counter = 0;

    public int selectQueue() {
        int ret = counter;
        counter = (counter + 1) % numberOfQueues;
        return ret;
    }
}
