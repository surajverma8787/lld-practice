package main

func main() {
	println("###### E-commerce Demo ######")

	iphone := NewIphoneProductObservable(
		"ip15",
		"iPhone 15",
		1250,
		10,
	)

	johnPush := NewPushNotificationObserver(
		"John123",
		"JohnDeviceP1",
	)

	janeEmail := NewEmailNotificationObserver(
		"Jane783",
		"jane783@gmail.com",
	)

	iphone.Purchase(10)

	success := iphone.Purchase(1)

	if !success {
		iphone.AddObserver(johnPush)
		iphone.AddObserver(janeEmail)
	}

	iphone.Restock(20)
}
