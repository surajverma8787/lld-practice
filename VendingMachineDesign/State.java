import java.util.List;

public abstract class State {
    public void clickOnInsertCoin(VendingMachine vendingMachine) throws Exception {}
    public void insertCoins(VendingMachine vendingMachine, Coin coin) throws Exception {}
    public void selectProductButton(VendingMachine vendingMachine) throws  Exception {}
    public void chooseProduct(VendingMachine vendingMachine, int code) throws Exception {}
    public Item dispenseProduct(VendingMachine vendingMachine, int code) throws Exception { return null; }
    public int getChange(int money) throws Exception { return 0; }
    public List<Coin> getFullRefund(VendingMachine vendingMachine) throws Exception {
        return null;
    }
}