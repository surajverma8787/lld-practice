import java.util.List;

public class ExpenseController {
    BalanceSheetController balanceSheetController;

    public ExpenseController() {
        balanceSheetController = new BalanceSheetController();
    }

    public Expense createExpense(String expenseId, String description, double expenseAmount, List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser) {
        ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);

        try {
            expenseSplit.validateSplitRequest(splitDetails, expenseAmount);
        } catch (Exception e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);

        balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);

        return expense;
    }
}
