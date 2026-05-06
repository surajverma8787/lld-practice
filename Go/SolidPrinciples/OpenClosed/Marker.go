package main

type Marker struct {
	Name  string
	Color string
	Price int
	Year  int
}

func NewMarker(name, color string, price int, year int) *Marker {
	return &Marker{
		Name:  name,
		Color: color,
		Price: price,
		Year:  year,
	}
}

func (m *Marker) SetName(name string) {
	m.Name = name
}

func (m *Marker) SetColor(color string) {
	m.Color = color
}

func (m *Marker) SetPrice(price int) {
	m.Price = price
}

func (m *Marker) SetYear(year int) {
	m.Year = year
}

func (m *Marker) GetPrice() int {
	return m.Price
}
