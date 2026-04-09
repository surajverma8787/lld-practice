import java.util.List;
import java.util.ArrayList;

interface WeatherObservable {
    void addWeatherObserver(WeatherNotificationObserver observer);
    void removeWeatherObserver(WeatherNotificationObserver observer);
    void notifyWeatherObservers();
    void setWeatherReadings(float temperature, float humidity, float
pressure);
}

interface WeatherNotificationObserver {
    void update();
}

class WeatherStation implements WeatherObservable {
    private float temperature;
    private float humidity;
    private float pressure;
    private final List<WeatherNotificationObserver> weatherObservers = new ArrayList<>();

    @Override
    public void addWeatherObserver(WeatherNotificationObserver observer) {
        weatherObservers.add(observer);
        System.out.println("[+] Observer registered: " + observer.getClass().getSimpleName());
    }

    @Override
    public void removeWeatherObserver(WeatherNotificationObserver observer) {
        weatherObservers.remove(observer);
    }

    @Override 
    public void notifyWeatherObservers() {
        for (WeatherNotificationObserver observer : weatherObservers) {
            observer.update();
        }
    }

    @Override
    public void setWeatherReadings(float temperature, float humidity, float
pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyWeatherObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }
}

class CurrentConditionsDisplay implements WeatherNotificationObserver {
    private final WeatherObservable weatherStation;

    public CurrentConditionsDisplay(WeatherObservable weatherStation) {
        this.weatherStation = weatherStation;
    }

    @Override
    public void update() {
        System.out.println("Saving weather data... ");
        display();
    }

    // Display the current weather conditions
    public void display() {
        System.out.println("Current Weather Conditions: ");
    }
}

class ForecastDisplay implements WeatherNotificationObserver {
    private final WeatherObservable weatherStation;

    public ForecastDisplay(WeatherObservable weatherStation) {
        this.weatherStation = weatherStation;
    }

    @Override
    public void update() {
        System.out.println("Updating weather data to do some analytics: ");
        display();
    }

    // Display the current weather conditions
    public void display() {
        System.out.println("Forecasting details: ");
    }
}

class EmailWeatherNotificationObserver implements WeatherNotificationObserver {

    private final String email;
    private final WeatherObservable weatherStation;

    public EmailWeatherNotificationObserver(String email, WeatherObservable weatherStation) {
        this.email = email;
        this.weatherStation = weatherStation;
    }

    @Override
    public void update() {
        sendEmail();
    }

    private void sendEmail() {
        System.out.println("📧 Email sent to " + email + " - Weather updated!");
    }
}

class SMSNotificationObserver implements WeatherNotificationObserver {

    private final String phoneNumber;
    private final WeatherObservable weatherStation;

    public SMSNotificationObserver(String phoneNumber, WeatherObservable weatherStation) {
        this.phoneNumber = phoneNumber;
        this.weatherStation = weatherStation;
    }

    @Override
    public void update() {
        sendSMS();
    }

    private void sendSMS() {
        System.out.println("📱 SMS sent to " + phoneNumber + " - Weather updated!");
    }
}

public class ObserverPatternWeather {
   public static void main(String[] args) {
     WeatherObservable weatherStation = new WeatherStation();

    CurrentConditionsDisplay currentDisplay = new
CurrentConditionsDisplay(weatherStation);
    ForecastDisplay forecastDisplay = new
ForecastDisplay(weatherStation);

    weatherStation.addWeatherObserver(currentDisplay);
    weatherStation.addWeatherObserver(forecastDisplay);


    EmailWeatherNotificationObserver emailObserver =
                new EmailWeatherNotificationObserver("suraj@gmail.com", weatherStation);
    SMSNotificationObserver smsObserver =
                new SMSNotificationObserver("9876543210", weatherStation);

    weatherStation.addWeatherObserver(emailObserver);
    weatherStation.addWeatherObserver(smsObserver);

    weatherStation.setWeatherReadings(20, 30, 50);
   }
}