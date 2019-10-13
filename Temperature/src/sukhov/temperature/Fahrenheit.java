package sukhov.temperature;

public class Fahrenheit implements Scale {
    private double degrees;

    Fahrenheit(double degree) {
        this.degrees = degree;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public double getCelsius() {
        return (degrees - 32) * 5 / 9;
    }

    @Override
    public double getFahrenheit() {
        return degrees;
    }

    @Override
    public double getKelvin() {
        return (degrees + 459.67) * 5 / 9;
    }

    @Override
    public double getReaumer() {
        return (degrees - 32) * 5 / 9;
    }
}
