public class DispenseProduct extends State {
    public DispenseProduct() {
        System.out.println("Dispense State");
    }

    public DispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        dispenseProduct(machine, codeNumber);
    }

    @Override
    public Item dispenseProduct(VendingMachine vendingMachine, int code) throws Exception {
        System.out.println("Product has been dispensed");
        vendingMachine.getInventory().getItemByCode(code).setAvailability(false);
        vendingMachine.setVendingMachineState(new VendingIdleState(vendingMachine));

        return vendingMachine.getInventory().getItemByCode(code);
    }
}