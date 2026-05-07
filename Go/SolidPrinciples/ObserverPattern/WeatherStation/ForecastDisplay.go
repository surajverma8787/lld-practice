package main

type ForecastDisplay struct{}

func (f *ForecastDisplay) Update(
	data WeatherData,
) {

	println("Updating weather analytics...")
	f.Display(data)
}

func (f *ForecastDisplay) Display(
	data WeatherData,
) {

	println("Forecast Details:")
	println("Pressure trend:", data.Pressure)
}
