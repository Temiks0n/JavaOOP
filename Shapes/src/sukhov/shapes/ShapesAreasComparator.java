package sukhov.shapes;

import java.util.Comparator;

public class ShapesAreasComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}