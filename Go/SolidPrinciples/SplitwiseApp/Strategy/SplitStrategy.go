package Strategy

import (
	Models2 "solid-principles/SplitwiseApp/Models"
)

type SplitStrategy interface {
	ValidateSplitRequest(splits []*Models2.Split, totalAmount float64) error
}
