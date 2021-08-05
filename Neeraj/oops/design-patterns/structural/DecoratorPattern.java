import java.util.*;
import java.io.*;

/**
 * @implNote <br>
 * • You have a Pizza interface, that has two methods. <br>
 * ○ getDescription() -> Gives the Ingredients of the Pizza.<br>
 * ○ getCost() -> Gives the cost of the pizza.<br>
 * • You can have a ToppingDecorator class, that will help you wrap toppings to existing Pizza object.<br>
 * Now you can create multiple wrapping Objects, that helps you wrap the new Topping details to an existing pizza object.<br>
 */

public class DecoratorPattern {

    public static void main(String[] args) {
        Pizza mozzarellaPizza = new Mozzarella(new BasicPizza());
        System.out.println("Ingredients :" + mozzarellaPizza.getDescription());
        System.out.println("Cost :" + mozzarellaPizza.getCost());

        Pizza tomatoSaucePizza = new TomatoSauce(new BasicPizza());
        System.out.println("Ingredients :" + tomatoSaucePizza.getDescription());
        System.out.println("Cost :" + tomatoSaucePizza.getCost());

        Pizza mozzarellaTomatoSaucePizza = new Mozzarella(new TomatoSauce(new BasicPizza()));
        System.out.println("Ingredients :" + mozzarellaTomatoSaucePizza.getDescription());
        System.out.println("Cost :" + mozzarellaTomatoSaucePizza.getCost());
    }
}

interface Pizza {
    String getDescription();

    double getCost();
}

abstract class ToppingDecorator implements Pizza {
    Pizza basePizza;

    public ToppingDecorator(Pizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public String getDescription() {
        return basePizza.getDescription();
    }

    @Override
    public double getCost() {
        return basePizza.getCost();
    }
}

class BasicPizza implements Pizza {

    public BasicPizza() {
        System.out.println("\tPreparing:Building Basic Pizza Crust");
    }

    @Override
    public String getDescription() {
        return "Plain Crust";
    }

    @Override
    public double getCost() {
        return 4.0;
    }
}

class Mozzarella extends ToppingDecorator {

    public Mozzarella(Pizza basePizza) {
        super(basePizza);
        System.out.println("\tPreparing:Adding Mozzarella.");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Mozzarella";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }
}

class TomatoSauce extends ToppingDecorator {

    public TomatoSauce(Pizza basePizza) {
        super(basePizza);
        System.out.println("\tPreparing:Adding Tomato Sauce.");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Tomato Sauce";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}