package main

type SMSNotificationObserver struct {
	phoneNumber string
}

func NewSMSObserver(phone string) *SMSNotificationObserver {
	return &SMSNotificationObserver{
		phoneNumber: phone,
	}
}

func (s *SMSNotificationObserver) Update(
	data WeatherData,
) {

	println(
		"SMS SENT to:",
		s.phoneNumber,
		"- Weather updated!",
	)
}
