package main

type CurrentConditionsDisplay struct{}

func (c *CurrentConditionsDisplay) Update(
	data WeatherData,
) {

	println("Saving weather data...")
	c.Display(data)
}

func (c *CurrentConditionsDisplay) Display(
	data WeatherData,
) {

	println("Current Weather Conditions:")
	println("Temperature:", data.Temperature)
	println("Humidity:", data.Humidity)
	println("Pressure:", data.Pressure)
}
