package main

type StockNotificationObserver interface {
	Update()
	GetUserID() string
}
