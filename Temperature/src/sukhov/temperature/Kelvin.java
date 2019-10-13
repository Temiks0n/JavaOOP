package sukhov.temperature;

public class Kelvin implements Scale {
    private double degrees;

    Kelvin(double degree) {
        this.degrees = degree;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public double getCelsius() {
        return degrees - 273.15;
    }

    @Override
    public double getFahrenheit() {
        return degrees * 5 / 9 - 459.67;
    }

    @Override
    public double getKelvin() {
        return degrees;
    }

    @Override
    public double getReaumer() {
        return (degrees - 273.15) * 4 / 5;
    }
}
