package Services

import (
	"errors"

	"solid-principles/SplitwiseApp/Models"
	Strategy "solid-principles/SplitwiseApp/Strategy"
)

type SplitService struct{}

func NewSplitService() *SplitService {
	return &SplitService{}
}

func (s *SplitService) CreateEqualSplits(
	users []*Models.User,
	totalAmount float64,
) ([]*Models.Split, error) {

	if len(users) == 0 {
		return nil, errors.New("users list cannot be empty")
	}

	splits := []*Models.Split{}

	expectedAmount :=
		totalAmount / float64(len(users))

	for _, user := range users {

		splits = append(splits, &Models.Split{
			User:       user,
			Amount:     expectedAmount,
			Percentage: 0.0,
		})
	}

	strategy, err :=
		Strategy.GetSplitStrategy(Models.EQUAL)

	if err != nil {
		return nil, err
	}

	err = strategy.ValidateSplitRequest(
		splits,
		totalAmount,
	)

	if err != nil {
		return nil, err
	}

	return splits, nil
}

func (s *SplitService) CreateUnequalSplits(
	users []*Models.User,
	amounts []float64,
	totalAmount float64,
) ([]*Models.Split, error) {

	if len(users) == 0 {
		return nil, errors.New("users list cannot be empty")
	}

	if len(users) != len(amounts) {
		return nil, errors.New(
			"users and amounts size mismatch",
		)
	}

	splits := []*Models.Split{}

	for i, user := range users {

		splits = append(splits, &Models.Split{
			User:       user,
			Amount:     amounts[i],
			Percentage: 0.0,
		})
	}

	strategy, err :=
		Strategy.GetSplitStrategy(Models.UNEQUAL)

	if err != nil {
		return nil, err
	}

	err = strategy.ValidateSplitRequest(
		splits,
		totalAmount,
	)

	if err != nil {
		return nil, err
	}

	return splits, nil
}

func (s *SplitService) CreatePercentageSplits(
	users []*Models.User,
	percentages []float64,
	totalAmount float64,
) ([]*Models.Split, error) {

	if len(users) == 0 {
		return nil, errors.New("users list cannot be empty")
	}

	if len(users) != len(percentages) {
		return nil, errors.New(
			"users and percentages size mismatch",
		)
	}

	splits := []*Models.Split{}

	for i, user := range users {

		amount :=
			(totalAmount * percentages[i]) / 100

		splits = append(splits, &Models.Split{
			User:       user,
			Amount:     amount,
			Percentage: percentages[i],
		})
	}

	strategy, err :=
		Strategy.GetSplitStrategy(Models.PERCENTAGE)

	if err != nil {
		return nil, err
	}

	err = strategy.ValidateSplitRequest(
		splits,
		totalAmount,
	)

	if err != nil {
		return nil, err
	}

	return splits, nil
}
