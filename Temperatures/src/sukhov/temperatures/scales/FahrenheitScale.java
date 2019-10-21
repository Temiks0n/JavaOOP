package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesBox;

public class FahrenheitScale implements Scale {
    private double degrees;
    private final ScalesBox scalesBox = ScalesBox.FAHRENHEIT;

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
    public ScalesBox getScalesBox() {
        return scalesBox;
    }

    @Override
    public double calculation(Scale scale) {
        if (scale.getScalesBox() == scalesBox) {
            return degrees;
        } else if (scale.getScalesBox() == ScalesBox.CELSIUS) {
            return (degrees - 32) * 5 / 9;
        } else if (scale.getScalesBox() == ScalesBox.KELVIN) {
            return (degrees + 459.67) * 5 / 9;
        } else if (scale.getScalesBox() == ScalesBox.REAUMUR) {
            return (degrees - 32) * 4 / 9;
        }

        return 0;
    }
}
