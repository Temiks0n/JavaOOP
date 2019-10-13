package sukhov.temperature;

public interface Scale {

    void setDegrees(double degrees);

    double getCelsius();

    double getFahrenheit();

    double getKelvin();

    double getReaumer();
}
