// // Abstractions
// interface Keyboard {
//     void getSpecifications();
// }

// interface Mouse {
//     void getSpecifications();
// }

// // Low-level modules (concrete implementations)

// class WiredKeyboard implements Keyboard {
//     private final String connectionType;
//     private final String company;
//     private final String modelVersion;
//     private final String color;

//     public WiredKeyboard(String connectionType, String company, String modelVersion, String color) {
//         this.connectionType = connectionType;
//         this.company = company;
//         this.modelVersion = modelVersion;
//         this.color = color;
//     }

//     public void getSpecifications() {
//         System.out.println("===> Wired Keyboard");
//         System.out.println("Connection Type: " + connectionType);
//         System.out.println("Company: " + company);
//         System.out.println("Model Version: " + modelVersion);
//         System.out.println("Color: " + color);
//     }
// }

// class BluetoothKeyboard implements Keyboard {
//     private final String connectionType;
//     private final String company;
//     private final String modelVersion;
//     private final String color;

//     public BluetoothKeyboard(String connectionType, String company, String modelVersion, String color) {
//         this.connectionType = connectionType;
//         this.company = company;
//         this.modelVersion = modelVersion;
//         this.color = color;
//     }

//     public void getSpecifications() {
//         System.out.println("===> Bluetooth Keyboard");
//         System.out.println("Connection Type: " + connectionType);
//         System.out.println("Company: " + company);
//         System.out.println("Model Version: " + modelVersion);
//         System.out.println("Color: " + color);
//     }
// }

// class WiredMouse implements Mouse {
//     private final String connectionType;
//     private final String company;
//     private final String modelVersion;
//     private final String color;

//     public WiredMouse(String connectionType, String company, String modelVersion, String color) {
//         this.connectionType = connectionType;
//         this.company = company;
//         this.modelVersion = modelVersion;
//         this.color = color;
//     }

//     public void getSpecifications() {
//         System.out.println("===> Wired Mouse");
//         System.out.println("Connection Type: " + connectionType);
//         System.out.println("Company: " + company);
//         System.out.println("Model Version: " + modelVersion);
//         System.out.println("Color: " + color);
//     }
// }

// class BluetoothMouse implements Mouse {
//     private final String connectionType;
//     private final String company;
//     private final String modelVersion;
//     private final String color;

//     public BluetoothMouse(String connectionType, String company, String modelVersion, String color) {
//         this.connectionType = connectionType;
//         this.company = company;
//         this.modelVersion = modelVersion;
//         this.color = color;
//     }

//     public void getSpecifications() {
//         System.out.println("===> Bluetooth Mouse");
//         System.out.println("Connection Type: " + connectionType);
//         System.out.println("Company: " + company);
//         System.out.println("Model Version: " + modelVersion);
//         System.out.println("Color: " + color);
//     }
// }

// // ❌ High-level module tightly coupled to low-level modules
// class MacBook {
//     private final WiredKeyboard keyboard;
//     private final WiredMouse mouse;

//     // Direct dependency on concrete classes
//     public MacBook(WiredKeyboard keyboard, WiredMouse mouse) {
//         this.keyboard = keyboard;
//         this.mouse = mouse;
//     }

//     public Mouse getMouse() {
//         return mouse;
//     }

//     public Keyboard getKeyboard() {
//         return keyboard;
//     }
// }

// // Demo showing problem
// public class DependencyInversion {
//     public static void main(String[] args) {

//         WiredKeyboard wiredKeyboard = new WiredKeyboard("USB", "Dell", "F602", "Grey");
//         WiredMouse wiredMouse = new WiredMouse("USB", "Dell", "F602", "Grey");

//         BluetoothKeyboard bluetoothKeyboard = new BluetoothKeyboard("Bluetooth", "Logitech", "G102", "Black");
//         BluetoothMouse bluetoothMouse = new BluetoothMouse("Bluetooth", "Logitech", "G102", "Black");

//         // Works only with wired components
//         MacBook macBook = new MacBook(wiredKeyboard, wiredMouse);
//         macBook.getKeyboard().getSpecifications();
//         macBook.getMouse().getSpecifications();

//         // ❌ Cannot use bluetooth components without modifying MacBook
//         // MacBook macBook2 = new MacBook(bluetoothKeyboard, bluetoothMouse);
//     }
// }

// Abstractions remain the same
public interface Keyboard {
    void getSpecifications();
}

public interface Mouse {
    void getSpecifications();
}

// Low-level modules (same as before)

class WiredKeyboard implements Keyboard {
    private final String connectionType;
    private final String company;
    private final String modelVersion;
    private final String color;

    public WiredKeyboard(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Wired Keyboard");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model Version: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

class BluetoothKeyboard implements Keyboard {
    private final String connectionType;
    private final String company;
    private final String modelVersion;
    private final String color;

    public BluetoothKeyboard(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Bluetooth Keyboard");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model Version: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

class WiredMouse implements Mouse {
    private final String connectionType;
    private final String company;
    private final String modelVersion;
    private final String color;

    public WiredMouse(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Wired Mouse");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model Version: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

class BluetoothMouse implements Mouse {
    private final String connectionType;
    private final String company;
    private final String modelVersion;
    private final String color;

    public BluetoothMouse(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Bluetooth Mouse");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model Version: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

// ✅ High-level module depends on abstractions, not concrete classes
class MacBook {
    private final Keyboard keyboard;
    private final Mouse mouse;

    // Dependency Injection via constructor
    public MacBook(Mouse mouse, Keyboard keyboard) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }
}

// Usage example
class DependencyInversion {
    public static void main(String[] args) {

        WiredKeyboard wiredKeyboard = new WiredKeyboard("USB", "Dell", "F602", "Grey");
        WiredMouse wiredMouse = new WiredMouse("USB", "Dell", "F602", "Grey");

        BluetoothKeyboard bluetoothKeyboard = new BluetoothKeyboard("Bluetooth", "Logitech", "G102", "Black");
        BluetoothMouse bluetoothMouse = new BluetoothMouse("Bluetooth", "Logitech", "G102", "Black");

        // Works with wired components
        MacBook macBook1 = new MacBook(wiredMouse, wiredKeyboard);
        macBook1.getKeyboard().getSpecifications();
        macBook1.getMouse().getSpecifications();

        // Works with bluetooth components (no change needed in MacBook)
        MacBook macBook2 = new MacBook(bluetoothMouse, bluetoothKeyboard);
        macBook2.getKeyboard().getSpecifications();
        macBook2.getMouse().getSpecifications();
    }
}