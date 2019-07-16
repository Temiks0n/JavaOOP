package sukhov.shapes.main;

import sukhov.shapes.Shape;

import java.util.Comparator;

public class ShapesPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}
