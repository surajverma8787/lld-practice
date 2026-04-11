public class Card {
    int pinNumber = 123456;
    int cvv = 401;
    int expiryDate = 1;
    UserBankAccount bankAccount;
    String holderName = "Suraj";

    public boolean validatePin(int pinNumber) {
        return this.pinNumber == pinNumber;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public int getBankBalance() {
        return this.bankAccount.getBalance();
    }

    public void deductBankBalance(int amount) {
        bankAccount.withdrawalBalance(amount);
    }

    public void setBankAccount(UserBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}