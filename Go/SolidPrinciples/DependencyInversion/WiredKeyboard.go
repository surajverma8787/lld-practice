package main

type WiredKeyboard struct {
	connectionType string
	company        string
	modelVersion   string
	color          string
}

func NewWiredKeyboard(conn, company, model, color string) *WiredKeyboard {
	return &WiredKeyboard{conn, company, model, color}
}

func (w *WiredKeyboard) GetSpecifications() {
	println("===> Wired Keyboard")
	println("Connection:", w.connectionType)
	println("Company:", w.company)
	println("Model:", w.modelVersion)
	println("Color:", w.color)
}
