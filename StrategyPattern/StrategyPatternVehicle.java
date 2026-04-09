// Different Drive Modes in a Vehicle
// Without Strategy Pattern

// class Vehicle {
//     public void drive() {
//         System.out.println("Normal Drive");
//     }
// }

// class SportsVehicle extends Vehicle {
//     @Override
//     public void drive() {
//           System.out.println("Sports Drive");
//     }
// }

// class OffRoadVehicle extends Vehicle {
//     @Override
//     public void drive() {
//           System.out.println("Sports Drive"); // code duplication
//     }
// }

// class PassengerVehicle extends Vehicle {
//     // No override since its normal drive inheriting from base class 
// }

// public class StrategyPatternVehicle {
//     public static void main(String[] args) {
//         Vehicle vehicle = new Vehicle(); 

//         vehicle = new SportsVehicle();
//         vehicle.drive();

//         vehicle = new OffRoadVehicle();
//         vehicle.drive();

//         vehicle = new PassengerVehicle();
//         vehicle.drive();
//     }
// }

// With Strategy Pattern

interface DriveStrategy {
    public void drive();
}

class NormalDrive implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Normal Drive");
    }
}

class SportsDrive implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Sports Drive");
    }
}

class Vehicle1 {
    DriveStrategy driveStrategy;

    public Vehicle1(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}

class SportsVehicle extends Vehicle1 {
    public SportsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

class GoodsVehicle extends Vehicle1 {
    public GoodsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

class OffRoadVehicle extends Vehicle1 {
    public OffRoadVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

public class StrategyPatternVehicle {
    public static void main(String[] args) {
        Vehicle1 vehicle = new SportsVehicle(new SportsDrive());
        vehicle.drive();

        vehicle = new GoodsVehicle(new NormalDrive());
        vehicle.drive();

        vehicle = new OffRoadVehicle(new SportsDrive());
        vehicle.drive();
    }
}





