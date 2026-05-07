package main

type MilkDecorator struct {
	CoffeeDecorator
}

func NewMilkDecorator(coffee Coffee) *MilkDecorator {
	return &MilkDecorator{
		CoffeeDecorator{
			coffee: coffee,
		},
	}
}

func (m *MilkDecorator) Cost() int {
	return m.coffee.Cost() + 20
}

func (m *MilkDecorator) Description() string {
	return m.coffee.Description() + ", Milk"
}
