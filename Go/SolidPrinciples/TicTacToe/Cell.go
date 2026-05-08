package main

type Cell struct {
	row   int
	col   int
	value string
}

func NewCell(row, col int) *Cell {
	return &Cell{row: row, col: col, value: "-"}
}

func (c *Cell) GetRow() int {
	return c.row
}

func (c *Cell) GetCol() int {
	return c.col
}

func (c *Cell) GetValue() string {
	return c.value
}

func (c *Cell) SetValue(value string) {
	c.value = value
}

func (c *Cell) isEmpty() bool {
	return c.value == "-"
}
