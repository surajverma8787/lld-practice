import java.util.List;

public class SelectionState extends State {
    public SelectionState() {
        System.out.println("Selection");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int code) throws Exception {
       boolean isAvail =  machine.getInventory().isItemAvailableByCode(code);

       if(!isAvail) {
           System.out.println("Item not available");
           throw new Exception("Item not available");
       }

       int totalCoins = 0;
       Item item = machine.getInventory().getItemByCode(code);
       for(Coin coin: machine.getCoinList()) {
           totalCoins += coin.getValue();
       }

       if(totalCoins < item.getItemPrice()) {
           System.out.println("Insufficient Amount");
           getFullRefund(machine);
           throw new Exception("insufficient amount");
       } else {
           if(totalCoins > item.getItemPrice()) {
               getChange(totalCoins-item.getItemPrice());
           }
           machine.setVendingMachineState(new DispenseProduct(machine, code));
       }
    }

    @Override
    public int getChange(int returnExtraMoney) throws Exception{
        System.out.println("Returned the change in the Coin Dispense Tray: " + returnExtraMoney);
        return returnExtraMoney;
    }

    @Override
    public List<Coin> getFullRefund(VendingMachine machine) {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new VendingIdleState(machine));
        return machine.getCoinList();
    }
}