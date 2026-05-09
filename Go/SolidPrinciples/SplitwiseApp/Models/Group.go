package Models

type Group struct {
	GroupID string
	Name    string

	Users    []*User
	Expenses []*Expense
}

func NewGroup(
	groupID string,
	name string,
) *Group {

	return &Group{
		GroupID:  groupID,
		Name:     name,
		Users:    []*User{},
		Expenses: []*Expense{},
	}
}

func (g *Group) AddUser(user *User) {
	g.Users = append(g.Users, user)
}

func (g *Group) AddExpense(expense *Expense) {
	g.Expenses = append(g.Expenses, expense)
}

func (g *Group) GetGroupID() string {
	return g.GroupID
}

func (g *Group) GetUsers() []*User {
	return g.Users
}

func (g *Group) GetExpenses() []*Expense {
	return g.Expenses
}
