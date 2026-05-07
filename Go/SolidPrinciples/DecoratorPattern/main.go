package main

func main() {

	var coffee Coffee = &SimpleCoffee{}

	coffee = NewMilkDecorator(coffee)
	coffee = NewSugarDecorator(coffee)

	println(
		coffee.Description(),
		"=",
		coffee.Cost(),
	)
}
