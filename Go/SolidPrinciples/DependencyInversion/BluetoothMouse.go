package main

type BluetoothMouse struct {
	connectionType string
	company        string
	modelVersion   string
	color          string
}

func NewBluetoothMouse(conn, company, model, color string) *BluetoothMouse {
	return &BluetoothMouse{conn, company, model, color}
}

func (b *BluetoothMouse) GetSpecifications() {
	println("===> Bluetooth Mouse")
}
