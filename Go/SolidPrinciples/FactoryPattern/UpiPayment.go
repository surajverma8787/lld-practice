package main

type UpiPayment struct{}

func (u *UpiPayment) Pay() {
	println("Paying via UPI")
}
