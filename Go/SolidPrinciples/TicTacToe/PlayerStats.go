package main

type PlayerStats struct {
	rows     []int
	cols     []int
	diagonal int
	antiDiag int
}

func NewPlayerStats(size int) *PlayerStats {
	return &PlayerStats{
		rows: make([]int, size),
		cols: make([]int, size),
	}
}
