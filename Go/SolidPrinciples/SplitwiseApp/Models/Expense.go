package Models

type Expense struct {
	ExpenseID string
	PaidBy    *User
	Amount    float64
	Splits    []*Split
	Remarks   string
	SplitType SplitType
}

func NewExpense(expenseId string, paidBy *User, amount float64, splits []*Split, remarks string, splitType SplitType) *Expense {
	return &Expense{expenseId, paidBy, amount, splits, remarks, splitType}
}
