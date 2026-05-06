package main

type MacBook struct {
	keyboard Keyboard
	mouse    Mouse
}

func NewMacBook(mouse Mouse, keyboard Keyboard) *MacBook {
	return &MacBook{
		keyboard: keyboard,
		mouse:    mouse,
	}
}

func (m *MacBook) GetKeyboard() Keyboard {
	return m.keyboard
}

func (m *MacBook) GetMouse() Mouse {
	return m.mouse
}
