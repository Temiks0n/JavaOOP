package range;

import java.util.Arrays;

public class RangeSet {
    private double fromA;
    private double toA;
    private double fromB;
    private double toB;

    public RangeSet(double fromA, double toA, double fromB, double toB) {
        this.fromA = fromA;
        this.toA = toA;
        this.fromB = fromB;
        this.toB = toB;
    }

    public Object getIntersectionInterval() {
        if (fromA > toB || toA < fromB) {
            return null;
        }

        double[] result = new double[2];
        if (fromA >= fromB) {
            result[0] = fromA;
        } else {
            result[0] = fromB;
        }
        if (toA <= toB) {
            result[1] = toA;
        } else {
            result[1] = toB;
        }
        return Arrays.toString(result);
    }

    public Object getUnionInterval() {
        if (toA < fromB) {
            double[][] result = {{fromA, toA}, {fromB, toB}};
            return Arrays.deepToString(result);
        }
        if (fromA > toB) {
            double[][] result = {{fromB, toB}, {fromA, toA}};
            return Arrays.deepToString(result);
        }

        double[] result = new double[2];
        if (fromA <= fromB) {
            result[0] = fromA;
        } else {
            result[0] = fromB;
        }
        if (toA >= toB) {
            result[1] = toA;
        } else {
            result[1] = toB;
        }
        return Arrays.toString(result);
    }

    public Object getDifferenceInterval() {
        if (fromA > toB || toA < fromB) {
            double[] result = {fromA, toA};
            return Arrays.toString(result);
        }

        if (fromA > fromB && toA < toB) {
            return null;
        }
        if (fromA < fromB && toA > toB) {
            double[][] result = {{fromA, fromB}, {toB, toA}};
            return Arrays.deepToString(result);
        }

        if (fromA <= fromB) {
            double[] array = {fromA, fromB};
            return Arrays.toString(array);
        } else {
            double[] array = {toB, toA};
            return Arrays.toString(array);
        }
    }
}
