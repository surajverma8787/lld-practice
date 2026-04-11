public class AtmIdleState extends ATMState {
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card is inserted");
        atm.setAtmCurrentState(new AtmCardState());
    }
}
