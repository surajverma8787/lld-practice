package main

type Bike interface {
	Accelerate()
	ApplyBrakes()
}

type Engine interface {
	TurnOnEngine()
	TurnOffEngine()
}

func Ride(b Bike) {
	b.Accelerate()
	b.ApplyBrakes()
}
