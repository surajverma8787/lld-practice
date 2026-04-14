import java.util.List;
import java.util.*;

public class PercentageExpenseSplit implements ExpenseSplit {

    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) throws Exception {

        if (splitList == null || splitList.isEmpty()) {
            throw new IllegalArgumentException("Split list cannot be empty");
        }

        double totalPercentage = 0;

        for (Split split : splitList) {

            if (split.getUser() == null) {
                throw new IllegalArgumentException("User cannot be null");
            }

            if (split.getPercentage() < 0) {
                throw new IllegalArgumentException("Percentage cannot be negative");
            }

            totalPercentage += split.getPercentage();
        }

        double epsilon = 0.01;

        if (Math.abs(totalPercentage - 100.0) > epsilon) {
            throw new IllegalArgumentException("Total percentage must be 100");
        }
    }
}