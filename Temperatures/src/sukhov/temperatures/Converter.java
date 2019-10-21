package sukhov.temperatures;

import sukhov.temperatures.scales.Scale;

public class Converter {
    private Scale scale;

    public Converter() {
    }

    public void input(double degree, Scale scale) {
        this.scale = scale;
        scale.setDegrees(degree);
    }

    public double output(Scale scale) {
        return this.scale.calculation(scale);
    }
}
