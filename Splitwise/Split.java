public class Split {
    User user;
    double amountOwe;
    double percentage = 0.0;

    public Split(User user, double amountOwe) {
        this.user = user;
        this.amountOwe = amountOwe;
        this.percentage = 0.0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmountOwe() {
        return amountOwe;
    }

    public void setAmountOwe(double amountOwe) {
        this.amountOwe = amountOwe;
    }

    public double getPercentage() {
        return percentage;
    }
}
