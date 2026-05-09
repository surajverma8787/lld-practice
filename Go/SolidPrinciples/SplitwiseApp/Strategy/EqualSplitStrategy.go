package Strategy

import (
	"fmt"
	"math"
	Models2 "solid-principles/SplitwiseApp/Models"
)

type EqualSplitStrategy struct{}

func (e *EqualSplitStrategy) ValidateSplitRequest(splits []*Models2.Split, totalAmount float64) error {
	if len(splits) == 0 {
		return fmt.Errorf("splits cannot be empty")
	}

	expectedAmount := (totalAmount) / (float64(len(splits)))

	const epsilon = 0.0001

	for _, split := range splits {
		if math.Abs(split.GetAmount()-expectedAmount) > epsilon {
			return fmt.Errorf("invalid equal split")
		}
	}

	return nil
}
