package main

type PaymentStrategy interface {
	ProcessPayment(amount float64)
}

func main() {
	// Credit Card
	cc := NewCreditCardPayment("1234-5678")

	processor := NewPaymentProcessor(cc)
	processor.Process(100)

	// Switch to Net Banking
	processor.SetStrategy(&NetBankingPayment{})
	processor.Process(200)

	// Switch to Cash
	processor.SetStrategy(&CashPayment{})
	processor.Process(50)
}
