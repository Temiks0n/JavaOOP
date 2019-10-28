package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesName;

public class CelsiusScale implements Scale {
    private double degrees;
    private ScalesName scalesName = ScalesName.CELSIUS;

    public CelsiusScale() {
    }

    CelsiusScale(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public ScalesName getScalesName() {
        return scalesName;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public double getCalculation(Scale scale) {
        CalculatorScale calculation = new CalculatorScale(degrees, scalesName, scale);

        return calculation.getCalculation();
    }
}
