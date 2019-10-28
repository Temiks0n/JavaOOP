package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesName;

public class CalculatorScale {
    private double degrees;
    private ScalesName scalesNameInput;
    private ScalesName scalesNameOutput;

    public CalculatorScale(double degrees, ScalesName scalesNameInput, Scale scale) {
        this.degrees = degrees;
        this.scalesNameInput = scalesNameInput;
        scalesNameOutput = scale.getScalesName();
    }

    public double getCalculation() {
        if (scalesNameInput == ScalesName.CELSIUS) {
            return getCelsius();
        } else if (scalesNameInput == ScalesName.FAHRENHEIT) {
            return getFahrenheit();
        } else if (scalesNameInput == ScalesName.KELVIN) {
            return getKelvin();
        } else if (scalesNameInput == ScalesName.REAUMUR) {
            return getReaumer();
        }

        return 0;
    }

    private double getCelsius() {
        if (scalesNameOutput == scalesNameInput) {
            return degrees;
        } else if (scalesNameOutput == ScalesName.FAHRENHEIT) {
            return 9 * degrees / 5 + 32;
        } else if (scalesNameOutput == ScalesName.KELVIN) {
            return degrees + 273.15;
        } else if (scalesNameOutput == ScalesName.REAUMUR) {
            return degrees * 4 / 5;
        }

        return 0;
    }

    private double getFahrenheit() {
        if (scalesNameOutput == scalesNameInput) {
            return degrees;
        } else if (scalesNameOutput == ScalesName.CELSIUS) {
            return (degrees - 32) * 5 / 9;
        } else if (scalesNameOutput == ScalesName.KELVIN) {
            return (degrees + 459.67) * 5 / 9;
        } else if (scalesNameOutput == ScalesName.REAUMUR) {
            return (degrees - 32) * 4 / 9;
        }

        return 0;
    }

    private double getKelvin() {
        if (scalesNameOutput == scalesNameInput) {
            return degrees;
        } else if (scalesNameOutput == ScalesName.CELSIUS) {
            return degrees - 273.15;
        } else if (scalesNameOutput == ScalesName.FAHRENHEIT) {
            return degrees * 9 / 5 - 459.67;
        } else if (scalesNameOutput == ScalesName.REAUMUR) {
            return (degrees - 273.15) * 4 / 5;
        }

        return 0;
    }

    private double getReaumer() {
        if (scalesNameOutput == scalesNameInput) {
            return degrees;
        } else if (scalesNameOutput == ScalesName.CELSIUS) {
            return degrees * 5 / 4;
        } else if (scalesNameOutput == ScalesName.FAHRENHEIT) {
            return degrees * 9 / 4 + 32;
        } else if (scalesNameOutput == ScalesName.KELVIN) {
            return degrees * 5 / 4 + 273.15;
        }

        return 0;
    }
}
