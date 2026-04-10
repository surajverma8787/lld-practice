import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    State vendingMachineState;
    List<Coin> coinList;
    Inventory inventory;

    public VendingMachine() {
        inventory = new Inventory();
        coinList = new ArrayList<>();
        vendingMachineState = new VendingIdleState();
    }

    public void setVendingMachineState(State vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void addCoin(Coin coin) {
        coinList.add(coin);
    }

    public void setCoinList(List<Coin> coins) {
        this.coinList = coins;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
