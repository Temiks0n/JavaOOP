package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesBox;

public class ReaumerScale implements Scale {
    private double degrees;
    private ScalesBox scalesBox = ScalesBox.REAUMUR;

    public ReaumerScale() {
    }

    public ReaumerScale(double degree) {
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
            return degrees * 9 / 4 + 32;
        } else if (scale.getScalesBox() == ScalesBox.FAHRENHEIT) {
            return degrees * 5 / 9 - 459.67;
        } else if (scale.getScalesBox() == ScalesBox.KELVIN) {
            return degrees * 5 / 4 + 273.15;
        }

        return 0;
    }
}
