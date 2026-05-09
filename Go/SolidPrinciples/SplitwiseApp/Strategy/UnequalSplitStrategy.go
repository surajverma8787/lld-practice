package Strategy

import (
	"fmt"
	"math"
	Models2 "solid-principles/SplitwiseApp/Models"
)

type UnequalSplitStrategy struct{}

func (u *UnequalSplitStrategy) ValidateSplitRequest(splits []*Models2.Split, totalAmount float64) error {
	if len(splits) == 0 {
		return fmt.Errorf("splits cannot be empty")
	}

	sum := 0.0
	for _, split := range splits {
		sum += split.GetAmount()
	}

	const epsilon = 0.0001

	if math.Abs(sum-totalAmount) > epsilon {
		return fmt.Errorf(
			"splits amount does not match total amount (%f)",
			totalAmount,
		)
	}

	return nil
}
