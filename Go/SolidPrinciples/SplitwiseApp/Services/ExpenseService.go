package Services

import (
	"fmt"
	"solid-principles/SplitwiseApp/Models"
)

type ExpenseService struct {
	expenses       []*Models.Expense
	splitService   *SplitService
	balanceService *BalanceService
}

func NewExpenseService(
	splitService *SplitService,
	balanceService *BalanceService,
) *ExpenseService {
	return &ExpenseService{
		expenses:       []*Models.Expense{},
		splitService:   splitService,
		balanceService: balanceService,
	}
}

func (service *ExpenseService) CreateExpense(
	paidBy *Models.User,
	users []*Models.User,
	totalAmount float64,
	splitType Models.SplitType,
	remarks string,
	amounts []float64,
	percentages []float64,
) (*Models.Expense, error) {

	var (
		splits []*Models.Split
		err    error
	)

	switch splitType {

	case Models.EQUAL:

		splits, err =
			service.splitService.CreateEqualSplits(
				users,
				totalAmount,
			)

	case Models.UNEQUAL:

		splits, err =
			service.splitService.CreateUnequalSplits(
				users,
				amounts,
				totalAmount,
			)

	case Models.PERCENTAGE:

		splits, err =
			service.splitService.CreatePercentageSplits(
				users,
				percentages,
				totalAmount,
			)

	default:
		return nil, fmt.Errorf("invalid split type")
	}

	if err != nil {
		return nil, err
	}

	expense := &Models.Expense{
		ExpenseID: "123",
		PaidBy:    paidBy,
		Amount:    totalAmount,
		Splits:    splits,
		Remarks:   remarks,
		SplitType: splitType,
	}

	service.expenses =
		append(service.expenses, expense)

	service.balanceService.UpdateBalance(
		paidBy,
		splits,
	)

	return expense, nil
}
