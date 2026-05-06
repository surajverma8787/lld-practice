package main

type NetBankingPayment struct{}

func (n *NetBankingPayment) ProcessPayment(amount float64) {
	println("Paying using Net Banking. Amount:", amount)
}
