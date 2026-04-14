import java.time.Duration;
import java.time.LocalDateTime;

public class MinutePricing implements PricingStrategy {
    private double ratePerMinute;

    public MinutePricing(double ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }

    @Override
    public double calculatePrice(LocalDateTime entryTime, LocalDateTime exitTime) {
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        if (minutes == 0) minutes = 1; // Minimum 1 hour charge
        return minutes * ratePerMinute;
    }

    public double getRatePerMinute() { return ratePerMinute; }
    public void setRatePerMinute(double ratePerHour) { this.ratePerMinute = ratePerMinute; }
}
