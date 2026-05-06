package main

type CashPayment struct{}

func (c *CashPayment) ProcessPayment(amount float64) {
	println("Paying using Cash. Amount:", amount)
}
