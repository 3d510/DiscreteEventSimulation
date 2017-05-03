// This is an abstract class which all classes that are used for defining real 
// process types inherit. The puropse is to make sure that they all define the 
// method treatSignal, which is needed in the main program.

import java.util.Random;

public abstract class Proc extends Global{
	public abstract void TreatSignal(Signal x);

	public double exp(double mean, Random randomSeed) {
		return -Math.log(1-randomSeed.nextDouble())*mean;
	}

	public double uniform(double mean, Random randomSeed) {
		return mean * 2 * randomSeed.nextDouble();
	}
}

