package Models

type Split struct {
	User       *User
	Amount     float64
	Percentage float64
}

func NewSplit(user *User, amount float64) *Split {
	return &Split{user, amount, 0}
}

func (s *Split) GetAmount() float64 {
	return s.Amount
}

func (s *Split) GetPercentage() float64 {
	return s.Percentage
}

func (s *Split) GetUser() *User {
	return s.User
}
