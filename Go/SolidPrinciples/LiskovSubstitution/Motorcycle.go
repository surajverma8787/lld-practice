package main

type MotorCycle struct {
	company    string
	speed      int
	isEngineOn bool
}

func NewMotorCycle(company string, speed int) *MotorCycle {
	return &MotorCycle{
		company: company,
		speed:   speed,
	}
}

// Engine behavior
func (m *MotorCycle) TurnOnEngine() {
	m.isEngineOn = true
	println("Engine is ON!")
}

func (m *MotorCycle) TurnOffEngine() {
	m.isEngineOn = false
	println("Engine is OFF!")
}

// Bike behavior
func (m *MotorCycle) Accelerate() {
	m.speed += 10
	println("MotorCycle Speed:", m.speed)
}

func (m *MotorCycle) ApplyBrakes() {
	m.speed -= 5
	println("MotorCycle Speed:", m.speed)
}
