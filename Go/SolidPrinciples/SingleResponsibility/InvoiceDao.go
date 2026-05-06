package main

type InvoiceDao struct {
	invoice *Invoice
}

func NewInvoiceDao(invoice *Invoice) *InvoiceDao {
	return &InvoiceDao{invoice: invoice}
}

func (dao *InvoiceDao) SaveToDB() {
	println("Saving to DB...")
}
