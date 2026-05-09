package Strategy

import (
	"fmt"
	"math"

	Models2 "solid-principles/SplitwiseApp/Models"
)

type PercentageSplitStrategy struct{}

func (p *PercentageSplitStrategy) ValidateSplitRequest(
	splits []*Models2.Split,
	totalAmount float64,
) error {

	if len(splits) == 0 {
		return fmt.Errorf("splits cannot be empty")
	}

	totalPercentage := 0.0

	for _, split := range splits {

		if split.GetPercentage() < 0 {
			return fmt.Errorf("percentage cannot be negative")
		}

		if split.GetPercentage() > 100 {
			return fmt.Errorf("percentage cannot exceed 100")
		}

		totalPercentage += split.GetPercentage()
	}

	const epsilon = 0.0001

	if math.Abs(totalPercentage-100.0) > epsilon {
		return fmt.Errorf(
			"total percentage should be 100, got %f",
			totalPercentage,
		)
	}

	return nil
}
