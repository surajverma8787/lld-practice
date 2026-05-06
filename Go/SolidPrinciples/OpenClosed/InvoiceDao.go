package main

type InvoiceDao interface {
	Save(invoice *Invoice)
}

type DatabaseInvoiceDao struct{}

func (d *DatabaseInvoiceDao) Save(invoice *Invoice) {
	println("Saving to DB...")
}
