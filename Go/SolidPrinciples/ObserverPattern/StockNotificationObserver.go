package main

type StockNotificationObserver interface {
	Update()
	GetNotificationMethod() string
	GetUserID() string
}
