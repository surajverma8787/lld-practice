package main

func main() {

	player1 := NewPlayer("Suraj", "X")
	player2 := NewPlayer("Rahul", "O")

	players := []*Player{
		player1,
		player2,
	}

	game := NewGame(3, players)

	game.Start()
}
