package range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from && number <= to);
    }

    public Range getIntersectionInterval(double fromA, double toA, double fromB, double toB) {
        if (fromA > toB || toA < fromB) {
            return null;
        }

        return new Range(Math.max(fromA, fromB), Math.min(toA, toB));
    }

    public Range[] getUnionInterval(double fromA, double toA, double fromB, double toB) {
        if (toA < fromB) {
            Range[] union = new Range[2];
            union[0] = new Range(fromA, toA);
            union[1] = new Range(fromB, toB);
            return union;
        }
        if (fromA > toB) {
            Range[] union = new Range[2];
            union[0] = new Range(fromB, toB);
            union[1] = new Range(fromA, toA);
            return union;
        }

        Range[] union = new Range[1];
        union[0] = new Range(Math.min(fromA, fromB), Math.max(toA, toB));
        return union;
    }

    public Range[] getDifferenceInterval(double fromA, double toA, double fromB, double toB) {
        if (fromA > fromB && toA < toB) {
            return null;
        }
        if (fromA < fromB && toA > toB) {
            Range[] difference = new Range[2];
            difference[0] = new Range(fromA, fromB);
            difference[1] = new Range(toB, toA);
            return difference;
        }

        Range[] difference = new Range[1];
        if (fromA > toB || toA < fromB) {
            difference[0] = new Range(fromA, toA);
            return difference;
        }

        if (fromA <= fromB) {
            difference[0] = new Range(fromA, fromB);
            return difference;
        } else {
            difference[0] = new Range(toB, toA);
            return difference;
        }
    }
}