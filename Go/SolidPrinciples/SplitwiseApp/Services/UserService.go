package Services

import (
	"fmt"
	"solid-principles/SplitwiseApp/Models"
)

type UserService struct {
	users map[string]*Models.User
}

func NewUserService() *UserService {

	return &UserService{
		users: make(map[string]*Models.User),
	}
}

func (s *UserService) CreateUser(
	userID string,
	username string,
) (*Models.User, error) {

	if _, exists := s.users[userID]; exists {
		return nil, fmt.Errorf(
			"user with id %s already exists",
			userID,
		)
	}

	user := Models.NewUser(
		userID,
		username,
	)

	s.users[userID] = user

	return user, nil
}

func (s *UserService) GetUser(
	userID string,
) (*Models.User, error) {

	user, exists := s.users[userID]

	if !exists {
		return nil, fmt.Errorf(
			"user not found",
		)
	}

	return user, nil
}

func (s *UserService) GetAllUsers() []*Models.User {

	users := []*Models.User{}

	for _, user := range s.users {
		users = append(users, user)
	}

	return users
}
