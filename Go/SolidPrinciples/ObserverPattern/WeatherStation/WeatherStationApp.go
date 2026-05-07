package main

type WeatherStation struct {
	weatherData WeatherData
	observers   []WeatherObserver
}

func NewWeatherStation() *WeatherStation {
	return &WeatherStation{
		observers: []WeatherObserver{},
	}
}

func (w *WeatherStation) AddObserver(
	observer WeatherObserver,
) {
	w.observers = append(w.observers, observer)

	println("[+] Observer registered")
}

func (w *WeatherStation) RemoveObserver(index int) {

	if index < 0 || index >= len(w.observers) {
		return
	}

	w.observers = append(
		w.observers[:index],
		w.observers[index+1:]...,
	)
}

func (w *WeatherStation) NotifyObservers() {

	for _, observer := range w.observers {
		observer.Update(w.weatherData)
	}
}

func (w *WeatherStation) SetWeatherReadings(
	temp,
	humidity,
	pressure float32,
) {

	w.weatherData = WeatherData{
		Temperature: temp,
		Humidity:    humidity,
		Pressure:    pressure,
	}

	w.NotifyObservers()
}
