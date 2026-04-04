// ABSTRACT FACTORY PATTERN - COMPLETE CODE

// 1. Abstract Products
interface Button {
    void render();
}

interface Checkbox {
    void check();
}

// 2. Concrete Products - LIGHT THEME

class LightButton implements Button {
    public void render() {
        System.out.println("Light Theme Button");
    }
}

class LightCheckbox implements Checkbox {
    public void check() {
        System.out.println("Light Theme Checkbox");
    }
}

// 3. Concrete Products - DARK THEME

class DarkButton implements Button {
    public void render() {
        System.out.println("Dark Theme Button");
    }
}

class DarkCheckbox implements Checkbox {
    public void check() {
        System.out.println("Dark Theme Checkbox");
    }
}

// 4. Abstract Factory

interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 5. Concrete Factories

class LightThemeFactory implements UIFactory {

    public Button createButton() {
        return new LightButton();
    }

    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

class DarkThemeFactory implements UIFactory {

    public Button createButton() {
        return new DarkButton();
    }

    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}

// 6. Client Code

public class AbstractFactory {

    public static void main(String[] args) {

        String theme = "DARK"; // Change to "LIGHT" to test

        UIFactory factory;

        // Selecting factory (only place with decision logic)
        if (theme.equalsIgnoreCase("LIGHT")) {
            factory = new LightThemeFactory();
        } else {
            factory = new DarkThemeFactory();
        }

        // Creating family of objects
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.check();
    }
}