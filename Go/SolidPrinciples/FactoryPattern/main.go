package main

import "fmt"

func main() {
	paymentType := "UPI"

	payment, err := GetPayment(paymentType)

	if err != nil {
		fmt.Println(err)
		return
	}

	payment.Pay()
}
