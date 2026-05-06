package main

type CreditCardPayment struct {
	CardNumber string
}

func NewCreditCardPayment(cardNumber string) *CreditCardPayment {
	return &CreditCardPayment{
		CardNumber: cardNumber,
	}
}

func (c *CreditCardPayment) ProcessPayment(amount float64) {
	println("Paying using Credit Card:", c.CardNumber, "Amount:", amount)
}
