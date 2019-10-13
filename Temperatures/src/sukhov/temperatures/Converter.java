package sukhov.temperatures;

import sukhov.temperatures.gui.ScalesBox;

public class Converter {
    private Scale scale;

    public Converter() {
    }

    public void input(double degree, ScalesBox degreesBox) {
        switch (degreesBox) {
            case CELSIUS:
                scale = new Celsius(degree);
                break;
            case FAHRENHEIT:
                scale = new Fahrenheit(degree);
                break;
            case KELVIN:
                scale = new Kelvin(degree);
                break;
            case REAUMUR:
                scale = new Reaumer(degree);
                break;
        }
    }

    public double output(ScalesBox degreesBox) {
        switch (degreesBox) {
            case CELSIUS:
                return scale.getCelsius();
            case FAHRENHEIT:
                return scale.getFahrenheit();
            case KELVIN:
                return scale.getKelvin();
            case REAUMUR:
                return scale.getReaumer();
        }

        return -1;
    }
}
