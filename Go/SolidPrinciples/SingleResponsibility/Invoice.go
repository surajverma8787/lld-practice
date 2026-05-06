package main

type Invoice struct {
	Marker   *Marker
	Quantity int
	Total    int
}

func NewInvoice(marker *Marker, quantity int) *Invoice {
	return &Invoice{marker, quantity, 0}
}

func (i *Invoice) CalculateTotal() {
	println("Calculating Total..")
	i.Total = i.Quantity * i.Marker.GetPrice()
}

func (i *Invoice) GetTotal() int {
	return i.Total
}
