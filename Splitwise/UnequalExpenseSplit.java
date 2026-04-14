import java.util.List;

public class UnequalExpenseSplit implements ExpenseSplit {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) throws Exception {
        if (splitList == null || splitList.isEmpty()) {
            throw new IllegalArgumentException("Split list cannot be empty");
        }

        double sum = 0;

        for (Split split : splitList) {

            // Check null user
            if (split.getUser() == null) {
                throw new IllegalArgumentException("User cannot be null");
            }

            // Check negative amount
            if (split.getAmountOwe() < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }

            sum += split.getAmountOwe();
        }

        if (Math.round(sum * 100.0) / 100.0 !=
                Math.round(totalAmount * 100.0) / 100.0) {

            throw new IllegalArgumentException(
                    "Total split amount does not match total expense"
            );
        }
    }
}
