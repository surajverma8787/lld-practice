package main

type Coffee interface {
	Cost() int
	Description() string
}

type SimpleCoffee struct {
}

func (c *SimpleCoffee) Cost() int {
	return 100
}

func (c *SimpleCoffee) Description() string {
	return "Simple coffee"
}

type CoffeeDecorator struct {
	coffee Coffee
}
