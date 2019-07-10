package sukhov.shapes.main;

import sukhov.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static class ShapesAreasComparator implements Comparator<Shapes> {
        @Override
        public int compare(Shapes shape1, Shapes shape2) {
            return (int) (shape2.getArea() - shape1.getArea());
        }

        public static Shapes maxArea(Shapes[] array, int number) {
            Arrays.sort(array, new ShapesAreasComparator());
            return array[number - 1];
        }
    }

    public static class ShapesPerimeterComparator implements Comparator<Shapes> {
        @Override
        public int compare(Shapes shape1, Shapes shape2) {
            return (int) (shape2.getPerimeter() - shape1.getPerimeter());
        }

        public static Shapes maxPerimeter(Shapes[] array, int number) {
            Arrays.sort(array, new ShapesPerimeterComparator());
            return array[number - 1];
        }
    }

    public static void main(String[] args) {
        Shapes triangle1 = new Triangle(-5, -4, 2, 9, 6, 3);
        Shapes triangle2 = new Triangle(-3, 6, 3, 8, -3, -9);
        Shapes circle1 = new Circle(8);
        Shapes circle2 = new Circle(6);
        Shapes rectangle1 = new Rectangle(2, 8);
        Shapes rectangle2 = new Rectangle(3, 9);
        Shapes square1 = new Square(4);
        Shapes square2 = new Square(6);

        Shapes[] shapes = {triangle1, triangle2, circle1, circle2, rectangle1, rectangle2, square1, square2};
        System.out.println("Список фигур: " + Arrays.toString(shapes));

        Shapes maxArea = ShapesAreasComparator.maxArea(shapes, 1);
        Shapes secondMaxPerimeter = ShapesPerimeterComparator.maxPerimeter(shapes, 2);

        System.out.printf("Фигура с максимальной площадью: %s; площадь: %f; периметр: %f; высота: %f; ширина: %f%n", maxArea, maxArea.getArea(), maxArea.getPerimeter(), maxArea.getHeight(), maxArea.getWidth());
        System.out.printf("Фигура со второй по величине периметром: %s; площадь: %f; периметр: %f; высота: %f; ширина: %f%n", secondMaxPerimeter, secondMaxPerimeter.getArea(), secondMaxPerimeter.getPerimeter(), secondMaxPerimeter.getHeight(), secondMaxPerimeter.getWidth());
    }
}