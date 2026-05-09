package Services

import (
	"solid-principles/SplitwiseApp/Models"
)

type BalanceService struct {
	balanceSheet *Models.BalanceSheet
}

func NewBalanceService() *BalanceService {
	return &BalanceService{balanceSheet: &Models.BalanceSheet{
		Balances: make(map[string]map[string]float64),
	}}
}

func (b *BalanceService) UpdateBalance(paidBy *Models.User, splits []*Models.Split) {

	for _, split := range splits {
		if split.User.GetUsername() == paidBy.GetUserID() {
			continue
		}

		debtorID := split.User.GetUserID()
		creditorID := paidBy.GetUserID()

		if _, exists := b.balanceSheet.Balances[debtorID]; !exists {
			b.balanceSheet.Balances[debtorID] =
				make(map[string]float64)
		}

		b.balanceSheet.Balances[debtorID][creditorID] +=
			split.GetAmount()
	}
}

func (b *BalanceService) ShowBalanceForUser(user *Models.User) {

	userID := user.GetUserID()

	balances, exists :=
		b.balanceSheet.Balances[userID]

	if !exists || len(balances) == 0 {
		println("No balances for user", userID)
		return
	}

	println("Balances for user:", userID)

	for otherUserID, amount := range balances {

		println(
			userID,
			"owes",
			otherUserID,
			amount,
		)
	}
}

func (b *BalanceService) ShowAllBalances() {

	if len(b.balanceSheet.Balances) == 0 {
		println("No balances present")
		return
	}

	for debtorID, creditors := range b.balanceSheet.Balances {

		for creditorID, amount := range creditors {

			println(
				debtorID,
				"owes",
				creditorID,
				amount,
			)
		}
	}
}
