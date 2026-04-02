// // BAD: This design violates LSP
// public interface Bike {

//     void turnOnEngine();

//     void turnOffEngine();

//     void accelerate();

//     void applyBrakes();
// }

// // Subclass of Bike - implements all Bike class behavior
// public class MotorCycle implements Bike {
//     String company;
//     boolean isEngineOn;
//     int speed;

//     public MotorCycle(String company, int speed) {
//         this.company = company;
//         this.speed = speed;
//     }

//     @Override
//     public void turnOnEngine() {
//         this.isEngineOn = true; // turn on the engine!
//         System.out.println("Engine is ON!");
//     }

//     @Override
//     public void turnOffEngine() {
//         this.isEngineOn = false; // turn off the engine!
//         System.out.println("Engine is OFF!");
//     }

//     @Override
//     public void accelerate() {
//         this.speed = this.speed + 10; // increase the speed
//         System.out.println("MotorCycle Speed: " + this.speed);
//     }

//     @Override
//     public void applyBrakes() {
//         this.speed = this.speed - 5; // decrease the speed
//         System.out.println("MotorCycle Speed: " + this.speed);
//     }
// }

// // This class violates LSP!
// public class Bicycle implements Bike {
//     String brand;
//     Boolean hasGears;
//     int speed;

//     public Bicycle(String brand, Boolean hasGears, int speed) {
//         this.brand = brand;
//         this.hasGears = hasGears;
//         this.speed = speed;
//     }

//     // LSP Violation: Strengthening preconditions
//     // Bicycle changes the behavior of turnOnEngine
//     @Override
//     public void turnOnEngine() {
//         throw new AssertionError("Detail Message: Bicycle has no engine!");
//     }

//     // Bicycle changes the behavior of turnOffEngine
//     @Override
//     public void turnOffEngine() {
//         throw new AssertionError("Detail Message: Bicycle has no engine!");
//     }

//     @Override
//     public void accelerate() {
//         this.speed = this.speed + 10; // increase the speed
//         System.out.println("Bicycle Speed: " + this.speed);
//     }

//     @Override
//     public void applyBrakes() {
//         this.speed = this.speed - 5; // decrease the speed
//         System.out.println("Bicycle Speed: " + this.speed);
//     }
// }

// // Usage example - demonstrates the LSP violations
// public class Demo {
//     public static void main(String[] args) {

//         // create the objects
//         MotorCycle motorCycle = new MotorCycle("HeroHonda", 10);
//         Bicycle bicycle = new Bicycle("Hercules", true, 10);

//         // Works fine with MotorCycle
//         motorCycle.turnOnEngine();
//         motorCycle.accelerate();
//         motorCycle.applyBrakes();
//         motorCycle.turnOffEngine();

//         // Client expects same behavior with Bicycle (but fails)
//         bicycle.turnOnEngine(); // violates LSP
//         bicycle.accelerate();
//         bicycle.applyBrakes();
//         bicycle.turnOffEngine(); // violates LSP
//     }
// }

abstract class Bike {

    // All Bikes can do these things
    public abstract void accelerate();

    public abstract void applyBrakes();
}

interface Engine {
    void turnOnEngine();

    void turnOffEngine();
}

// Subclass of Bike - implements all Bike behavior + Engine behavior
class MotorCycle extends Bike implements Engine {
    String company;
    boolean isEngineOn;
    int speed;

    public MotorCycle(String company, int speed) {
        this.company = company;
        this.speed = speed;
    }

    @Override
    public void turnOnEngine() {
        this.isEngineOn = true; // turn on the engine!
        System.out.println("Engine is ON!");
    }

    @Override
    public void turnOffEngine() {
        this.isEngineOn = false; // turn off the engine!
        System.out.println("Engine is OFF!");
    }

    @Override
    public void accelerate() {
        this.speed = this.speed + 10; // increase the speed
        System.out.println("MotorCycle Speed: " + this.speed);
    }

    @Override
    public void applyBrakes() {
        this.speed = this.speed - 5; // decrease the speed
        System.out.println("MotorCycle Speed: " + this.speed);
    }
}

// GOOD: Following LSP
// Bicycles don’t have engines, so no Engine interface
class Bicycle extends Bike {

    String brand;
    Boolean hasGears;
    int speed;

    public Bicycle(String brand, Boolean hasGears, int speed) {
        this.brand = brand;
        this.hasGears = hasGears;
        this.speed = speed;
    }

    @Override
    public void accelerate() {
        this.speed = this.speed + 10; // increase the speed
        System.out.println("Bicycle Speed: " + this.speed);
    }

    @Override
    public void applyBrakes() {
        this.speed = this.speed - 5; // decrease the speed
        System.out.println("Bicycle Speed: " + this.speed);
    }
}

// Usage of LSP-compliant design
public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {

        // create the objects
        MotorCycle motorCycle = new MotorCycle("HeroHonda", 10);
        Bicycle bicycle = new Bicycle("Hercules", true, 10);

        // Works fine with MotorCycle
        motorCycle.turnOnEngine();
        motorCycle.accelerate();
        motorCycle.applyBrakes();
        motorCycle.turnOffEngine();

        // Works fine with Bicycle
        bicycle.accelerate();
        bicycle.applyBrakes();
    }
}