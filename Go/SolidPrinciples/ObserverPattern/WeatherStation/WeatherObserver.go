package main

type WeatherData struct {
	Temperature float32
	Humidity    float32
	Pressure    float32
}

type WeatherObserver interface {
	Update(data WeatherData)
}
