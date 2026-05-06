package main

type InvoicePrinter struct {
	invoice *Invoice
}

func NewInvoicePrinter(invoice *Invoice) *InvoicePrinter {
	return &InvoicePrinter{invoice: invoice}
}

func (p *InvoicePrinter) Print() {
	println("Printing invoice...")
}
