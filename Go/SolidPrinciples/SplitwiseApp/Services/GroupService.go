package Services

import (
	"fmt"

	"solid-principles/SplitwiseApp/Models"
)

type GroupService struct {
	groups         map[string]*Models.Group
	expenseService *ExpenseService
}

func NewGroupService(
	expenseService *ExpenseService,
) *GroupService {

	return &GroupService{
		groups:         make(map[string]*Models.Group),
		expenseService: expenseService,
	}
}

func (s *GroupService) CreateGroup(
	groupID string,
	name string,
) (*Models.Group, error) {

	if _, exists := s.groups[groupID]; exists {

		return nil, fmt.Errorf(
			"group already exists",
		)
	}

	group := Models.NewGroup(
		groupID,
		name,
	)

	s.groups[groupID] = group

	return group, nil
}

func (s *GroupService) AddUserToGroup(
	groupID string,
	user *Models.User,
) error {

	group, exists := s.groups[groupID]

	if !exists {
		return fmt.Errorf(
			"group not found",
		)
	}

	group.AddUser(user)

	return nil
}

func (s *GroupService) GetGroup(
	groupID string,
) (*Models.Group, error) {

	group, exists := s.groups[groupID]

	if !exists {
		return nil, fmt.Errorf(
			"group not found",
		)
	}

	return group, nil
}

func (s *GroupService) CreateExpenseInGroup(
	groupID string,
	paidBy *Models.User,
	users []*Models.User,
	totalAmount float64,
	splitType Models.SplitType,
	remarks string,
	amounts []float64,
	percentages []float64,
) (*Models.Expense, error) {

	group, exists := s.groups[groupID]

	if !exists {
		return nil, fmt.Errorf(
			"group not found",
		)
	}

	expense, err :=
		s.expenseService.CreateExpense(
			paidBy,
			users,
			totalAmount,
			splitType,
			remarks,
			amounts,
			percentages,
		)

	if err != nil {
		return nil, err
	}

	group.AddExpense(expense)

	return expense, nil
}
