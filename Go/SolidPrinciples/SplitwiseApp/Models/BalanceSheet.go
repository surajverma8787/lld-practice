package Models

type BalanceSheet struct {
	Balances map[string]map[string]float64
}

func NewBalanceSheet() *BalanceSheet {
	return &BalanceSheet{
		Balances: make(map[string]map[string]float64),
	}
}
