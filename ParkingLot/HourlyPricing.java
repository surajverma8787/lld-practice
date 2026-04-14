import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPricing implements PricingStrategy {
    private double ratePerHour;

    public HourlyPricing(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculatePrice(LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toHours();
        if (hours == 0) hours = 1; // Minimum 1 hour charge
        return hours * ratePerHour;
    }

    public double getRatePerHour() { return ratePerHour; }
    public void setRatePerHour(double ratePerHour) { this.ratePerHour = ratePerHour; }
}
