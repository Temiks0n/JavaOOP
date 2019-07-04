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

    private Object getTwoInterval() {
        if (toA < fromB) {
            double[][] result = {{fromA, toA}, {fromB, toB}};
            return Arrays.deepToString(result);
        } else {
            double[][] result = {{fromB, toB}, {fromA, toA}};
            return Arrays.deepToString(result);
        }
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
        if (fromA > toB || toA < fromB) {
            return getTwoInterval();
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
            return getTwoInterval();
        }

        if (fromA <= fromB) {
            double[][] array = {{fromA, fromB}, {toA, toB}};
            return Arrays.deepToString(array);
        } else {
            double[][] array = {{fromB, fromA}, {toB, toA}};
            return Arrays.deepToString(array);
        }
    }
}
