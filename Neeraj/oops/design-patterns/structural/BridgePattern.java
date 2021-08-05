package structural;

import java.util.*;
import java.io.*;

/**
 * @implNote <br>
 * • We have a Shape abstract class (abstracter), and we have an DrawAPI interface (implementor). In order to Get a Shape we have to pass a DrawAPI as an input. <br>
 * ○ Shape class can have different implementations like Circle, Square, etc.<br>
 * ○ DrawAPI also can have many implementations, like RedCircle, GreenCircle, BlueSquare, etc.<br>
 * • Now while creating the Circle Shape object you don’t have to worry about the implementation of RedCircle.<br>
 * That part is abstracted out. That is the main implementation of Bridge pattern.<br>
 */
public class BridgePattern {

    public static void main(String[] args) {
        Shape redCircle = new Circle(new DrawRedCircle(), 0, 0, 5);
        redCircle.draw();
        Shape greenCircle = new Circle(new DrawGreenCircle(), 10, 10, 5);
        greenCircle.draw();

        Shape blueSquare = new Circle(new DrawBlueSquare(), 1, 1, 5);
        blueSquare.draw();
    }
}

abstract class Shape {
    DrawAPI drawAPI;

    public Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    abstract void draw();
}

class Circle extends Shape {
    int x, y, radius;

    public Circle(DrawAPI drawAPI, int x, int y, int radius) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    void draw() {
        drawAPI.draw(x, y, radius);
    }
}

interface DrawAPI {
    void draw(int x, int y, int len);
}

class DrawRedCircle implements DrawAPI {
    @Override
    public void draw(int x, int y, int radius) {
        System.out.println("Drawing RED circle. \t\tStaring from [" + x + "," + y + "], of radius " + radius);
    }
}

class DrawGreenCircle implements DrawAPI {
    @Override
    public void draw(int x, int y, int radius) {
        System.out.println("Drawing GREEN circle. \t\tStaring from [" + x + "," + y + "], of radius " + radius);
    }
}

class DrawBlueSquare implements DrawAPI {
    @Override
    public void draw(int x, int y, int radius) {
        System.out.println("Drawing BLUE square. \t\tStaring from [" + x + "," + y + "], of length " + radius);
    }
}