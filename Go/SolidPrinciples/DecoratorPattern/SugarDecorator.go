package main

type SugarDecorator struct {
	CoffeeDecorator
}

func NewSugarDecorator(coffee Coffee) *SugarDecorator {
	return &SugarDecorator{
		CoffeeDecorator{
			coffee: coffee,
		},
	}
}

func (s *SugarDecorator) Cost() int {
	return s.coffee.Cost() + 10
}

func (s *SugarDecorator) Description() string {
	return s.coffee.Description() + ", Sugar"
}
