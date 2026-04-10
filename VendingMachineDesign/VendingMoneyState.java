import java.util.List;

public class VendingMoneyState extends State {

    public VendingMoneyState() {
        System.out.println("Money State");
    }

    public void selectProductButton(VendingMachine machine) {
        machine.setVendingMachineState(new SelectionState());
    }

    @Override
    public void insertCoins(VendingMachine machine, Coin coin) {
        machine.addCoin(coin);
    }

    @Override
    public List<Coin> getFullRefund(VendingMachine machine) {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new VendingIdleState(machine));
        return machine.getCoinList();
    }
}