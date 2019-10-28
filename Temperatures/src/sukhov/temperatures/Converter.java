package sukhov.temperatures;

import sukhov.temperatures.scales.Scale;

public class Converter {
    private Scale scale;

    public void getInput(double degree, Scale scale) {
        this.scale = scale;
        scale.setDegrees(degree);
    }

    public double getOutput(Scale scale) {
        return this.scale.getCalculation(scale);
    }
}
