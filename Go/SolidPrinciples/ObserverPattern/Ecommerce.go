package main

type IphoneProductObservable struct {
	productID     string
	productName   string
	price         float64
	stockQuantity int
	observers     []StockNotificationObserver
}

func NewIphoneProductObservable(
	productID string,
	productName string,
	price float64,
	stockQuantity int,
) *IphoneProductObservable {

	return &IphoneProductObservable{
		productID:     productID,
		productName:   productName,
		price:         price,
		stockQuantity: stockQuantity,
		observers:     []StockNotificationObserver{},
	}
}

func (o *IphoneProductObservable) AddObserver(observer StockNotificationObserver) {
	o.observers = append(o.observers, observer)

	println("[+]",
		observer.GetUserID(),
		"subscribed for notifications on",
		o.productName)
}

func (o *IphoneProductObservable) RemoveObserver(userID string) {
	var updatedObservers []StockNotificationObserver
	for _, observer := range o.observers {
		if observer.GetUserID() != userID {
			updatedObservers = append(updatedObservers, observer)
		}
	}

	o.observers = updatedObservers
	println("[-]", userID, "unsubscribed")
}

func (o *IphoneProductObservable) NotifyObservers() {
	if o.stockQuantity > 0 && len(o.observers) > 0 {
		println("Notifying", len(o.observers), "subscribers...")

		for _, observer := range o.observers {
			observer.Update()
		}
	}
}

func (o *IphoneProductObservable) Purchase(quantity int) bool {
	if quantity > o.stockQuantity {
		println(
			"PURCHASE FAILED:",
			o.productName,
			"is out of stock!",
		)

		return false
	}

	println(
		"PURCHASE SUCCESS:",
		quantity,
		"units purchased",
	)

	o.stockQuantity -= quantity
	return true
}

func (o *IphoneProductObservable) Restock(quantity int) {
	wasOutOfStock := o.stockQuantity == 0

	println(
		"RESTOCKED:",
		o.productName,
		"Added",
		quantity,
		"items",
	)

	o.stockQuantity += quantity

	if wasOutOfStock && o.stockQuantity > 0 {
		o.NotifyObservers()
	}
}

type EmailNotificationObserver struct {
	userID      string
	emailAdress string
}

func NewEmailNotificationObserver(
	userID string,
	email string,
) *EmailNotificationObserver {

	return &EmailNotificationObserver{
		userID:      userID,
		emailAdress: email,
	}
}

func (o *EmailNotificationObserver) Update() {
	println(
		"EMAIL SENT to:",
		o.emailAdress,
		"Product back in stock!",
	)
}

func (e *EmailNotificationObserver) GetNotificationMethod() string {
	return "Email"
}

func (e *EmailNotificationObserver) GetUserID() string {
	return e.userID
}

type PushNotificationObserver struct {
	userID      string
	deviceToken string
}

func NewPushNotificationObserver(
	userID string,
	deviceToken string,
) *PushNotificationObserver {

	return &PushNotificationObserver{
		userID:      userID,
		deviceToken: deviceToken,
	}
}

func (p *PushNotificationObserver) Update() {

	println(
		"PUSH NOTIFICATION SENT to:",
		p.deviceToken,
		"Product back in stock!",
	)
}

func (p *PushNotificationObserver) GetNotificationMethod() string {
	return "Push Notification"
}

func (p *PushNotificationObserver) GetUserID() string {
	return p.userID
}
