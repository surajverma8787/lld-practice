package main

type StockAvailabilityObservable interface {
	AddObserver(observer StockNotificationObserver)
	RemoveObserver(userID string)
	NotifyObservers()
	Purchase(quantity int) bool
	Restock(quantity int)
}
