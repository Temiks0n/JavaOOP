package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesName;

public class FahrenheitScale implements Scale {
    private double degrees;
    private final ScalesName scalesName = ScalesName.FAHRENHEIT;

    public FahrenheitScale() {
    }

    public FahrenheitScale(double degree) {
        this.degrees = degree;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public ScalesName getScalesName() {
        return scalesName;
    }

    @Override
    public double getCalculation(Scale scale) {
        CalculatorScale calculation = new CalculatorScale(degrees, scalesName, scale);

        return calculation.getCalculation();
    }
}
