public class CashWithdrawalState extends ATMState {
    public CashWithdrawalState() {
        System.out.println("Please enter the Withdrawal Amount");
    }

    @Override
    public void cashWithdrawal(ATM atm, Card card, int withdrawAmount) {
        if (atm.getAtmBalance() < withdrawAmount) {
            System.out.println("Insufficient fund in the ATM Machine");
            exit(atm);
        } else if (card.getBankBalance() < withdrawAmount) {
            System.out.println("Insufficient fund in the your Bank Account");
            exit(atm);
        } else {
            card.deductBankBalance(withdrawAmount);
            atm.deductATMBalance(withdrawAmount);

            CashWithdrawalProcessor withdrawProcessor =
                    new TwoThousandWithdrawalProcessor(new FiveHundredWithdrawalProcessor(new OneHundredWithdrawalProcessor(null)));

            withdrawProcessor.withdraw(atm, withdrawAmount);
            exit(atm);
        }
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
