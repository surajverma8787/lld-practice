package main

type EmailWeatherNotificationObserver struct {
	email string
}

func NewEmailObserver(email string) *EmailWeatherNotificationObserver {
	return &EmailWeatherNotificationObserver{
		email: email,
	}
}

func (e *EmailWeatherNotificationObserver) Update(
	data WeatherData,
) {

	println(
		"EMAIL SENT to:",
		e.email,
		"- Weather updated!",
	)
}
