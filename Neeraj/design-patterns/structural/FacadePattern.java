package structural;

import java.util.*;
import java.io.*;

/**
 * @implNote <br>
 * • Assume that you have a Shape interface, with method called draw(). This methods draws the shape.<br>
 * • You can have multiple implementation of the share, like. Circle, Rectangle, Square.<br>
 * • Now create a ShapeMaker class that has methods like:<br>
 * ○ drawCircle() -> This will call draw method on the circle Object.<br>
 * ○ drawRectange() -> This method will call draw method on the Rectangle object.<br>
 * ○ drawSquare() -> This method will call draw method on the Square Object.<br>
 * End client is exposed with ShapeMaker Object, this way we have decoupled the implementation logic with the client.<br>
 */

public class FacadePattern {

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }

    interface Shape {
        void draw();
    }

    static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Drawing Circle");
        }
    }

    static class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Drawing Rectangle");
        }
    }

    static class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Drawing Square");
        }
    }

    static class ShapeMaker {
        Shape circle, rectangle, square;

        public ShapeMaker() {
            circle = new Circle();
            rectangle = new Rectangle();
            square = new Square();
        }

        public void drawCircle() {
            circle.draw();
        }

        public void drawRectangle() {
            rectangle.draw();
        }

        public void drawSquare() {
            square.draw();
        }
    }
}