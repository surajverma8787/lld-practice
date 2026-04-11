public class CheckBalanceState extends ATMState {
    public CheckBalanceState() {
        System.out.println("Checking Balance");
    }

    @Override
    public void displayBalance(ATM atm, Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    @Override
    public void exit(ATM atmObject) {
        returnCard();
        atmObject.setAtmCurrentState(new AtmIdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard() {
        System.out.println("Please collect your card");
    }
}
