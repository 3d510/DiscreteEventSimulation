
public class SimulationData {
    int numberOfServers;
    double serviceTime;
    double arrivalRate;
    double measureTime;
    int numberOfMeasure;

    public SimulationData(int numberOfServers, double serviceTime, double arrivalRate, double measureTime,
            int numberOfMeasure) {
        this.numberOfMeasure = numberOfMeasure;
        this.numberOfServers = numberOfServers;
        this.serviceTime = serviceTime;
        this.arrivalRate = arrivalRate;
        this.measureTime = measureTime;
    }

    public void show() {
        System.out.println(numberOfServers);
        System.out.println(serviceTime);
        System.out.println(arrivalRate);
        System.out.println(measureTime);
        System.out.println(numberOfMeasure);
    }
}
