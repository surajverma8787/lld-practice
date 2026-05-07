package main

type WeatherObservable interface {
	AddObserver(a WeatherObserver)
	RemoveObserver(index int)
	NotifyObservers()
	SetWeatherReadings(temp float32, humidity float32, pressure float32)
}
