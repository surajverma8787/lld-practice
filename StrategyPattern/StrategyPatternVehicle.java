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





