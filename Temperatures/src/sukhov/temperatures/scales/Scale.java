package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesName;

public interface Scale {
    void setDegrees(double degrees);

    ScalesName getScalesName();

    double getCalculation(Scale scale);
}
