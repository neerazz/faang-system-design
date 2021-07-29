package factory;

import factory.shapes.Shape;

public class Main {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.createShape("Circle");
        shape.draw();

        shape = factory.createShape("Rectangle");
        shape.draw();
    }
}
