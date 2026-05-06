package main

func main() {
	// create components
	wiredKeyboard := NewWiredKeyboard("USB", "Dell", "F602", "Grey")
	wiredMouse := NewWiredMouse("USB", "Dell", "F602", "Grey")

	bluetoothKeyboard := NewBluetoothKeyboard("Bluetooth", "Logitech", "G102", "Black")
	bluetoothMouse := NewBluetoothMouse("Bluetooth", "Logitech", "G102", "Black")

	mac1 := NewMacBook(wiredMouse, wiredKeyboard)
	mac2 := NewMacBook(bluetoothMouse, bluetoothKeyboard)

	mac1.GetKeyboard().GetSpecifications()
	mac1.GetMouse().GetSpecifications()

	mac2.GetKeyboard().GetSpecifications()
	mac2.GetMouse().GetSpecifications()
}
