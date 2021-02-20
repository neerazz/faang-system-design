package creational;

import java.util.*;
import java.io.*;


public class ProtoTypePattern {

    public static void main(String[] args) {
//        Initially all the prototype objects are stored in a cash or in JVM memory, when the system starts.
        Tree prototype = new Tree();

        Tree clone1 = prototype.clone();
        clone1.setColour("Red");

        Tree clone2 = prototype.clone();
        clone2.setColour("Blue");
        clone2.setName("Avatar");
    }
}

class Tree {
    String name, colour;

    public Tree clone() {
//        Clone can be a deep or a shallow clone.
        return new Tree();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}