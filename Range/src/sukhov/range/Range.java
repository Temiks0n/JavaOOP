package sukhov.range;

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

    public Range getIntersectionInterval(Range rangeB) {
        if (from >= rangeB.getTo() || to <= rangeB.getFrom()) {
            return null;
        }

        return new Range(Math.max(from, rangeB.getFrom()), Math.min(to, rangeB.getTo()));
    }

    public Range[] getUnionInterval(Range rangeB) {
        if (to < rangeB.getFrom()) {
            return new Range[]{new Range(from, to), new Range(rangeB.getFrom(), rangeB.getTo())};
        }
        if (from > rangeB.getTo()) {
            return new Range[]{new Range(rangeB.getFrom(), rangeB.getTo()), new Range(from, to)};
        }

        return new Range[]{new Range(Math.min(from, rangeB.getFrom()), Math.max(to, rangeB.getTo()))};
    }

    public Range[] getDifferenceInterval(Range range) {
        if (from >= range.getFrom() && to <= range.getTo()) {
            return null;
        }
        if (from >= range.getTo() || to <= range.getFrom()) {
            return null;
        }

        if (from < range.getFrom() && to > range.getTo()) {
            return new Range[]{new Range(from, range.getFrom()), new Range(range.getTo(), to)};
        }

        if (from <= range.getFrom()) {
            return new Range[]{new Range(from, range.getFrom())};
        } else {
            return new Range[]{new Range(range.getTo(), to)};
        }
    }

    public String toString() {
        return "[" + from + "; " + to + "]";
    }
}