import java.util.ArrayList;

public class VendingIdleState extends State {
    public VendingIdleState() {
        System.out.println("Idle State");
    }

    public VendingIdleState(VendingMachine machine) {
        machine.setCoinList(new ArrayList<>());
    }

    @Override
    public void clickOnInsertCoin(VendingMachine vendingMachine) throws Exception {
        vendingMachine.setVendingMachineState(new VendingMoneyState());
    }
}