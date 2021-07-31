public class FactoryPatternTester {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.createShape("Circle");
        shape.draw();

        shape = factory.createShape("Rectangle");
        shape.draw();
    }
}
