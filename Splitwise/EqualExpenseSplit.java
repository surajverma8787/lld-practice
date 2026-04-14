import java.util.List;

public class EqualExpenseSplit implements ExpenseSplit {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) throws Exception {
        double amountShouldBePresent = totalAmount / splitList.size();
        for(Split split: splitList) {
            if(split.getAmountOwe() != amountShouldBePresent) {
                System.out.println("Wrong SplitType");
                throw new Exception("Wrong equal split");
            }
        }
    }
}
