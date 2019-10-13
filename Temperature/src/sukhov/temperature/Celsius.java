package sukhov.temperature;

public class Celsius implements Scale {
    private double degrees;

    Celsius(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public double getCelsius() {
        return degrees;
    }

    @Override
    public double getFahrenheit() {
        return 9 * degrees / 5 + 32;
    }

    @Override
    public double getKelvin() {
        return degrees + 273.15;
    }

    @Override
    public double getReaumer() {
        return degrees * 4 / 5;
    }
}
