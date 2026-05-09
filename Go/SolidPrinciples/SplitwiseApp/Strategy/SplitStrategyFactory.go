package Strategy

import (
	"fmt"

	"solid-principles/SplitwiseApp/Models"
)

type SplitStrategyFactory struct{}

func GetSplitStrategy(
	splitType Models.SplitType,
) (SplitStrategy, error) {

	switch splitType {

	case "EQUAL":
		return &EqualSplitStrategy{}, nil

	case "UNEQUAL":
		return &UnequalSplitStrategy{}, nil

	case "PERCENTAGE":
		return &PercentageSplitStrategy{}, nil

	default:
		return nil, fmt.Errorf("invalid split type")
	}
}
