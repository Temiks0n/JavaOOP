package sukhov.temperature;

public class Reaumer implements Scale {
    private double degrees;

    Reaumer(double degree) {
        this.degrees = degree;
    }

    @Override
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public double getCelsius() {
        return degrees * 5 / 4;
    }

    @Override
    public double getFahrenheit() {
        return degrees * 9 / 4 + 32;
    }

    @Override
    public double getKelvin() {
        return degrees * 5 / 4 + 273.15;
    }

    @Override
    public double getReaumer() {
        return degrees;
    }
}
