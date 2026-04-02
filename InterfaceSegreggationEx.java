// BAD: This interface violates ISP (fat interface)
// interface RestaurantEmployee {

//     void prepareFood();

//     void decideMenu();

//     void serveFoodAndDrinks();

//     void takeOrder();

//     void cleanTheKitchen();
// }

// // BAD: Waiter is forced to implement methods it doesn't need
// class Waiter implements RestaurantEmployee {

//     @Override
//     public void takeOrder() {
//         System.out.println("Taking order...");
//     }

//     @Override
//     public void serveFoodAndDrinks() {
//         System.out.println("Serving food and drinks...");
//     }

//     @Override
//     public void cleanTheKitchen() {
//         // Forced implementation - doesn't make sense
//         throw new AssertionError("Waiter cannot clean the kitchen!");
//     }

//     @Override
//     public void prepareFood() {
//         // Forced implementation - doesn't make sense
//         throw new AssertionError("Waiter cannot prepare food!");
//     }

//     @Override
//     public void decideMenu() {
//         // Forced implementation - doesn't make sense
//         throw new AssertionError("Waiter cannot decide the menu!");
//     }
// }

// // Usage example showing the problem
// public class InterfaceSegreggation {
//     public static void main(String[] args) {

//         Waiter waiter = new Waiter();

//         // Valid operations
//         waiter.takeOrder();
//         waiter.serveFoodAndDrinks();

//         // Invalid operations (runtime failure)
//         waiter.prepareFood();
//         waiter.decideMenu();
//         waiter.cleanTheKitchen();
//     }
// }

// Focused interfaces (small and specific)

// For chefs
interface ChefTasks {
    void prepareFood();
    void decideMenu();
}

// For waiters
interface WaiterTasks {
    void serveFoodAndDrinks();
    void takeOrder();
}

// For maintenance staff
interface MaintenanceTasks {
    void cleanTheKitchen();
    void reStockGroceries();
}

// Chef only implements what it needs
class Chef implements ChefTasks {

    @Override
    public void prepareFood() {
        System.out.println("Preparing food...");
    }

    @Override
    public void decideMenu() {
        System.out.println("Deciding menu...");
    }
}

// Waiter only implements what it needs
class Waiter implements WaiterTasks {

    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Serving food and drinks...");
    }

    @Override
    public void takeOrder() {
        System.out.println("Taking order...");
    }
}

// Usage example
public class InterfaceSegreggationEx {
    public static void main(String[] args) {

        Chef chef = new Chef();
        Waiter waiter = new Waiter();

        // Works perfectly - only relevant methods exist
        chef.prepareFood();
        chef.decideMenu();

        waiter.takeOrder();
        waiter.serveFoodAndDrinks();
    }
}