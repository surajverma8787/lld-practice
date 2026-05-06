package main

type WiredMouse struct {
	connectionType string
	company        string
	modelVersion   string
	color          string
}

func NewWiredMouse(conn, company, model, color string) *WiredMouse {
	return &WiredMouse{conn, company, model, color}
}

func (w *WiredMouse) GetSpecifications() {
	println("===> Wired Mouse")
}
