package main

type BluetoothKeyboard struct {
	connectionType string
	company        string
	modelVersion   string
	color          string
}

func NewBluetoothKeyboard(conn, company, model, color string) *BluetoothKeyboard {
	return &BluetoothKeyboard{conn, company, model, color}
}

func (b *BluetoothKeyboard) GetSpecifications() {
	println("===> Bluetooth Keyboard")
}
