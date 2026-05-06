package main

func main() {
	marker := NewMarker("Camlin", "Blue", 10, 2020)
	invoice := NewInvoice(marker, 10)

	invoice.CalculateTotal()

	var dao InvoiceDao

	// DB save
	dao = &DatabaseInvoiceDao{}
	dao.Save(invoice)

	// File save
	dao = &FileInvoiceDao{}
	dao.Save(invoice)
}
