package main

func main() {
	marker := NewMarker("Camlin", "Blue", 10, 2020)
	invoice := NewInvoice(marker, 10)
	invoice.CalculateTotal()

	dao := NewInvoiceDao(invoice)
	dao.SaveToDB()

	printer := NewInvoicePrinter(invoice)
	printer.Print()
}
