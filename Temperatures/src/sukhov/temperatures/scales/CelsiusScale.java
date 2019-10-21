package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesBox;

public class CelsiusScale implements Scale {
    private double degrees;
    private ScalesBox scalesBox = ScalesBox.CELSIUS;

    public CelsiusScale() {
    }

    CelsiusScale(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public ScalesBox getScalesBox() {
        return scalesBox;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public double calculation(Scale scale) {
        if (scale.getScalesBox() == scalesBox) {
            return degrees;
        } else if (scale.getScalesBox() == ScalesBox.FAHRENHEIT) {
            return 9 * degrees / 5 + 32;
        } else if (scale.getScalesBox() == ScalesBox.KELVIN) {
            return degrees + 273.15;
        } else if (scale.getScalesBox() == ScalesBox.REAUMUR) {
            return degrees * 4 / 5;
        }

        return 0;
    }
}
