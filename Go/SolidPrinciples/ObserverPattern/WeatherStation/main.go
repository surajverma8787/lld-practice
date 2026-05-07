package main

func main() {

	weatherStation := NewWeatherStation()

	currentDisplay := &CurrentConditionsDisplay{}
	forecastDisplay := &ForecastDisplay{}

	emailObserver := NewEmailObserver("suraj@gmail.com")
	smsObserver := NewSMSObserver("9876543210")

	weatherStation.AddObserver(currentDisplay)
	weatherStation.AddObserver(forecastDisplay)
	weatherStation.AddObserver(emailObserver)
	weatherStation.AddObserver(smsObserver)

	weatherStation.SetWeatherReadings(20, 30, 50)
}
