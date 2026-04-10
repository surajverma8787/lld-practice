public class VendingMachineDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Creating Vending Machine...");
            VendingMachine machine = new VendingMachine();

            // Add items to inventory
            Item item1 = new Item(1, 20);
            Item item2 = new Item(2, 30);

            machine.getInventory().addItem(item1);
            machine.getInventory().addItem(item2);

            System.out.println("\n--- User Interaction Start ---");

            // Step 1: Click insert coin
            machine.vendingMachineState.clickOnInsertCoin(machine);

            // Step 2: Insert coins
            machine.vendingMachineState.insertCoins(machine, new Coin(10));
            machine.vendingMachineState.insertCoins(machine, new Coin(10));
            machine.vendingMachineState.insertCoins(machine, new Coin(10));

            // Step 3: Click select product
            machine.vendingMachineState.selectProductButton(machine);

            // Step 4: Choose product (code = 1)
            machine.vendingMachineState.chooseProduct(machine, 1);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}