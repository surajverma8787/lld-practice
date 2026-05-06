package main

func main() {
	motorCycle := NewMotorCycle("HeroHonda", 10)
	bicycle := NewBicycle("Hercules", true, 10)

	// LSP in action
	Ride(motorCycle)
	Ride(bicycle)

	// Engine-specific behavior
	motorCycle.TurnOnEngine()
	motorCycle.TurnOffEngine()
}
