package main

import "fmt"

type Game struct {
	board              *Board
	players            []*Player
	currentPlayerIndex int
	gameOver           bool
}

func NewGame(size int, players []*Player) *Game {
	board := NewBoard(size)

	for _, player := range players {
		board.playerStats[player] = NewPlayerStats(size)
	}

	return &Game{
		board:              board,
		players:            players,
		currentPlayerIndex: 0,
		gameOver:           false,
	}
}

func (g *Game) Start() {
	for !g.gameOver {
		g.board.Print()

		player := g.players[g.currentPlayerIndex]

		fmt.Println(player.GetName() + "'s turn")

		var row, col int

		fmt.Print("Enter row: ")
		fmt.Scanln(&row)

		fmt.Print("Enter col: ")
		fmt.Scanln(&col)

		if err := g.board.MakeMove(row, col, player.GetSymbol()); err != nil {
			fmt.Println("Invalid move")
			continue
		}

		if g.board.CheckWinner(player, row, col) {
			fmt.Println("Player :- ", player.GetName(), "Won")
			g.SetGameOver()
		}

		if g.board.IsDraw() {
			fmt.Println("Player :- ", player.GetName(), "Draw")
			g.SetGameOver()
		}

		g.SwitchTurn()
	}
}

func (g *Game) SwitchTurn() {
	g.currentPlayerIndex = g.currentPlayerIndex ^ 1
}

func (g *Game) SetGameOver() {
	g.gameOver = true
}
