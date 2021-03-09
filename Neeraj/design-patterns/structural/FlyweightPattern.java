package structural;

import java.util.*;
import java.io.*;

/**
 * @implNote <br>
 * • Assume an interface Shape, with methods like draw:<br>
 * ○ That takes in staring position, colour and other required inputs.<br>
 * • The Shape can have many implementations like, Circle, Square.<br>
 * • Now have a ShapeFactory class that has all the shapes Stored.<br>
 * when a drawing of a shape is required, Just pass the inputs and call draw.<br>
 */

public class FlyweightPattern {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Circle redCircle = shapeFactory.getCircle("RED");
        redCircle.setStarting(0, 0, 10);
        redCircle.draw();

        Circle blueCircle = shapeFactory.getCircle("BLUE");
        blueCircle.setStarting(5, 5, 12);
        blueCircle.draw();

        Circle redCircle2 = shapeFactory.getCircle("RED");
        redCircle2.setStarting(10, 10, 5);
        redCircle2.draw();

        Circle blueCircle2 = shapeFactory.getCircle("BLUE");
        blueCircle2.setStarting(15, 15, 100);
        blueCircle2.draw();

        System.out.println("Both First Red Circle & Second Red Circle Objects are same :" + redCircle.equals(redCircle2));
        System.out.println("Both First Blue Circle & Second Blue Circle Objects are same :" + blueCircle.equals(blueCircle2));
        System.out.println("But Blue Circle & Red Circle Objects are not same :" + blueCircle.equals(redCircle));
    }

    interface Shape {
        void draw();
    }

    static class Circle implements Shape {
        int x, y, radius;
        String colour;

        public Circle(String colour) {
            this.colour = colour;
        }

        public void setStarting(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Drawing CIRCLE of colour : " + colour + ". Starting at: [" + x + "," + y + "] of Radius = " + radius);
        }
    }

    static class ShapeFactory {
        Map<String, Shape> circleMap = new HashMap<>();

        public Circle getCircle(String colour) {
            circleMap.putIfAbsent(colour, new Circle(colour));
            return (Circle) circleMap.get(colour);
        }
    }
}
