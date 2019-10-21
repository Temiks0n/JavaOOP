package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesBox;

public class KelvinScale implements Scale {
    private double degrees;
    private final ScalesBox scalesBox = ScalesBox.KELVIN;

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
    public ScalesBox getScalesBox() {
        return scalesBox;
    }

    @Override
    public double calculation(Scale scale) {
        if (scale.getScalesBox() == scalesBox) {
            return degrees;
        } else if (scale.getScalesBox() == ScalesBox.CELSIUS) {
            return degrees - 273.15;
        } else if (scale.getScalesBox() == ScalesBox.FAHRENHEIT) {
            return degrees * 5 / 9 - 459.67;
        } else if (scale.getScalesBox() == ScalesBox.REAUMUR) {
            return (degrees - 273.15) * 4 / 5;
        }

        return 0;
    }
}
