package main

type CreditCardPayment struct{}

func (c *CreditCardPayment) Pay() {
	println("Paying via Credit Card")
}
