// // WITHOUT DECORATOR PATTERN

// interface Coffee {
//     int cost();
//     String description();
// }

// // Base class
// class SimpleCoffee implements Coffee {
//     public int cost() {
//         return 100;
//     }

//     public String description() {
//         return "Simple Coffee";
//     }
// }

// // Different combinations (BAD SCALING)

// class CoffeeWithMilk extends SimpleCoffee {
//     public int cost() {
//         return super.cost() + 20;
//     }

//     public String description() {
//         return super.description() + ", Milk";
//     }
// }

// class CoffeeWithSugar extends SimpleCoffee {
//     public int cost() {
//         return super.cost() + 10;
//     }

//     public String description() {
//         return super.description() + ", Sugar";
//     }
// }

// class CoffeeWithMilkAndSugar extends SimpleCoffee {
//     public int cost() {
//         return super.cost() + 30;
//     }

//     public String description() {
//         return super.description() + ", Milk, Sugar";
//     }
// }

// // Main class
// public class DecoratorPattern {
//     public static void main(String[] args) {

//         Coffee coffee1 = new SimpleCoffee();
//         System.out.println(coffee1.description() + " = " + coffee1.cost());

//         Coffee coffee2 = new CoffeeWithMilk();
//         System.out.println(coffee2.description() + " = " + coffee2.cost());

//         Coffee coffee3 = new CoffeeWithSugar();
//         System.out.println(coffee3.description() + " = " + coffee3.cost());

//         Coffee coffee4 = new CoffeeWithMilkAndSugar();
//         System.out.println(coffee4.description() + " = " + coffee4.cost());
//     }
// }


/* With Decorator Pattern */

interface Coffee {
    int cost();
    String description();
}

// Base class
class SimpleCoffee implements Coffee {
    public int cost() {
        return 100;
    }

    public String description() {
        return "Simple Coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public int cost() {
        return coffee.cost() + 20;
    }

    public String description() {
        return coffee.description() + ", Milk";
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public int cost() {
        return coffee.cost() + 10;
    }

    public String description() {
        return coffee.description() + ", Sugar";
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.description() + " = " + coffee.cost());
    }
}

