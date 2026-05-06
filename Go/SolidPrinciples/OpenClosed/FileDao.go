package main

type FileInvoiceDao struct{}

func (f *FileInvoiceDao) Save(invoice *Invoice) {
	println("Saving to file...")
}
