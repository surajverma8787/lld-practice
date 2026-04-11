public class OneHundredWithdrawalProcessor extends CashWithdrawalProcessor {
    public OneHundredWithdrawalProcessor(CashWithdrawalProcessor nextCashWithdrawProcessor) {
        super(nextCashWithdrawProcessor);
    }

    public void withdraw(ATM atm, int withdrawalAmount) {
        int required = withdrawalAmount / 100;
        int balance = withdrawalAmount % 100;

        if (required <= atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(required);
        } else if(required > atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(atm.getNoOfOneHundredNotes());
            balance = balance + (required - atm.getNoOfOneHundredNotes()) * 100;
        }


        if (balance != 0) {
            System.out.println("Something went wrong");
        }
    }
}
