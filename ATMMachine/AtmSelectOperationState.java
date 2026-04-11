public class AtmSelectOperationState extends ATMState {
    public AtmSelectOperationState() {
        showOperations();
    }

    @Override
    public void selectOperation(ATM atm, Card card, TransactionType txnType) {
        switch (txnType) {
            case TransactionType.CASH_WITHDRAWAL:
            case TransactionType.BALANCE_CHECK:
            default:
                System.out.println("Invalid transactuion type");
                exit(atm);
        }
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

    private void showOperations() {
        System.out.println("Please select the Operation");
        TransactionType.showAllTransactionTypes();
    }
}
