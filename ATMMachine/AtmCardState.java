public class AtmCardState extends ATMState {

    public AtmCardState() {
        System.out.println("enter your card pin number");
    }

    @Override
    public void authenticatePin(ATM atm, Card card, int pin) {
        if(!card.validatePin(pin)) {
            System.out.println("Incorrect Pin Entered");
            atm.setAtmCurrentState(new AtmIdleState());
            return;
        }

        atm.setAtmCurrentState(new AtmSelectOperationState());
    }

    @Override
    public void exit(ATM atm) {
        returnCard();
        atm.setAtmCurrentState(new AtmIdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard() {
        System.out.println("Please collect your card");
    }
}
