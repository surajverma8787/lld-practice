package main

import "fmt"

type Board struct {
	size        int
	cells       [][]Cell
	playerStats map[*Player]*PlayerStats
	movesPlayed int
}

func NewBoard(size int) *Board {
	cells := make([][]Cell, size)

	for i := 0; i < size; i++ {
		cells[i] = make([]Cell, size)

		for j := 0; j < size; j++ {
			cells[i][j] = *NewCell(i, j)
		}
	}

	playerStats := make(map[*Player]*PlayerStats)
	return &Board{size, cells, playerStats, 0}
}

func (b *Board) GetCell(row, column int) *Cell {
	return &b.cells[row][column]
}

func (b *Board) IsValidMove(row, column int) bool {
	if row >= 0 && row < b.size && column >= 0 && column < b.size {
		return true
	}

	return false
}

func (b *Board) MakeMove(row, column int, symbol string) error {
	if !b.IsValidMove(row, column) {
		return fmt.Errorf("Invalid move at row %d, column %d", row, column)
	}

	if !b.GetCell(row, column).isEmpty() {
		return fmt.Errorf("Cannot place at row %d, column %d", row, column)
	}

	b.GetCell(row, column).SetValue(symbol)
	b.movesPlayed++
	return nil
}

func (b *Board) Print() {
	for i := 0; i < b.size; i++ {
		for j := 0; j < b.size; j++ {
			print(b.cells[i][j].GetValue(), " ")
		}
		println()
	}
}

func (b *Board) IsDraw() bool {
	return b.movesPlayed == b.size*b.size
}

func (b *Board) CheckWinner(player *Player, row, column int) bool {
	stats, exists := b.playerStats[player]

	if !exists {
		stats = NewPlayerStats(b.size)
		b.playerStats[player] = stats
	}

	stats.rows[row]++
	stats.cols[column]++

	if row == column {
		stats.diagonal++
	}

	if row+column == b.size-1 {
		stats.antiDiag++
	}

	if stats.rows[row] == b.size ||
		stats.cols[column] == b.size ||
		stats.diagonal == b.size ||
		stats.antiDiag == b.size {
		return true
	}

	return false
}
