package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesName;

public class KelvinScale implements Scale {
    private double degrees;
    private final ScalesName scalesName = ScalesName.KELVIN;

    public KelvinScale() {
    }

    public KelvinScale(double degree) {
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
