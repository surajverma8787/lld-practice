package main

type Bicycle struct {
	brand    string
	hasGears bool
	speed    int
}

func NewBicycle(brand string, hasGears bool, speed int) *Bicycle {
	return &Bicycle{
		brand:    brand,
		hasGears: hasGears,
		speed:    speed,
	}
}

func (b *Bicycle) Accelerate() {
	b.speed += 10
	println("Bicycle Speed:", b.speed)
}

func (b *Bicycle) ApplyBrakes() {
	b.speed -= 5
	println("Bicycle Speed:", b.speed)
}
