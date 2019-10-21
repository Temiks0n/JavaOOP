package sukhov.temperatures.scales;

import sukhov.temperatures.ScalesBox;

public interface Scale {
    void setDegrees(double degrees);

    ScalesBox getScalesBox();

    double calculation(Scale scale);
}
