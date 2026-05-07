package main

import "fmt"

func GetPayment(paymentType string) (Payment, error) {
	switch paymentType {

	case "UPI":
		return &UpiPayment{}, nil

	case "CARD":
		return &CreditCardPayment{}, nil

	default:
		return nil, fmt.Errorf("invalid payment type")
	}
}
