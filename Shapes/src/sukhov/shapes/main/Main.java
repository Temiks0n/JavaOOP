package sukhov.shapes.main;

import sukhov.shapes.*;

import java.util.Arrays;

public class Main {

    public static Shape sortArea(Shape[] array, int numberLargest) {
        Arrays.sort(array, new ShapesAreasComparator());
        return array[numberLargest - 1];
    }

    public static Shape sortPerimeter(Shape[] array, int numberLargest) {
        Arrays.sort(array, new ShapesPerimeterComparator());
        return array[numberLargest - 1];
    }

    public static void main(String[] args) {
        Shape triangle1 = new Triangle(-5, -4, 2, 9, 6, 3);
        Shape triangle2 = new Triangle(-3, 6, 3, 8, -3, -9);
        Shape circle1 = new Circle(8);
        Shape circle2 = new Circle(6);
        Shape rectangle1 = new Rectangle(2, 8);
        Shape rectangle2 = new Rectangle(3, 9);
        Shape square1 = new Square(4);
        Shape square2 = new Square(6);

        Shape[] shapes = {triangle1, triangle2, circle1, circle2, rectangle1, rectangle2, square1, square2};
        System.out.println("Список фигур: " + Arrays.toString(shapes));

        Shape maxArea = sortArea(shapes, 1);
        Shape secondMaxPerimeter = sortPerimeter(shapes, 2);

        System.out.printf("Фигура с максимальной площадью: %s; площадь: %f; периметр: %f; высота: %f; ширина: %f%n", maxArea, maxArea.getArea(), maxArea.getPerimeter(), maxArea.getHeight(), maxArea.getWidth());
        System.out.printf("Фигура со второй по величине периметром: %s; площадь: %f; периметр: %f; высота: %f; ширина: %f%n", secondMaxPerimeter, secondMaxPerimeter.getArea(), secondMaxPerimeter.getPerimeter(), secondMaxPerimeter.getHeight(), secondMaxPerimeter.getWidth());
    }
}