package main

type Player struct {
	name   string
	symbol string
}

func NewPlayer(name, symbol string) *Player {
	return &Player{
		name:   name,
		symbol: symbol,
	}
}

func (p *Player) GetSymbol() string {
	return p.symbol
}

func (p *Player) GetName() string {
	return p.name
}
