package Models

type User struct {
	userID   string
	username string
}

func NewUser(userID, username string) *User {
	return &User{userID, username}
}

func (u *User) GetUserID() string {
	return u.userID
}

func (u *User) GetUsername() string {
	return u.username
}
