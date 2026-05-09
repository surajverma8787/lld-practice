package main

import (
	"fmt"
	"solid-principles/SplitwiseApp/Models"
	"solid-principles/SplitwiseApp/Services"
)

func main() {

	// -------------------------
	// 1. Initialize Services
	// -------------------------
	userService := Services.NewUserService()
	balanceService := Services.NewBalanceService()
	splitService := Services.NewSplitService()
	expenseService := Services.NewExpenseService(splitService, balanceService)
	groupService := Services.NewGroupService(expenseService)

	// -------------------------
	// 2. Create Users
	// -------------------------
	u1, _ := userService.CreateUser("U1", "Alice")
	u2, _ := userService.CreateUser("U2", "Bob")
	u3, _ := userService.CreateUser("U3", "Charlie")

	fmt.Println("Users created:", u1.GetUsername(), u2.GetUsername(), u3.GetUsername())

	// -------------------------
	// 3. Create Group
	// -------------------------
	group, err := groupService.CreateGroup("G1", "Go Developers")
	if err != nil {
		panic(err)
	}

	// Add users to group
	_ = groupService.AddUserToGroup("G1", u1)
	_ = groupService.AddUserToGroup("G1", u2)
	_ = groupService.AddUserToGroup("G1", u3)

	fmt.Println("Group created:", group.Name)

	// -------------------------
	// 4. Create Expense (EQUAL SPLIT)
	// -------------------------
	expense, err := groupService.CreateExpenseInGroup(
		"G1",
		u1, // paid by Alice
		[]*Models.User{u1, u2, u3},
		900,
		Models.EQUAL,
		"Lunch",
		nil,
		nil,
	)

	if err != nil {
		panic(err)
	}

	fmt.Println("Expense created:", expense.ExpenseID)

	// -------------------------
	// 5. Show Balances
	// -------------------------
	fmt.Println("\n--- BALANCES ---")
	balanceService.ShowAllBalances()

	// -------------------------
	// 6. Create UNEQUAL Expense
	// -------------------------
	_, err = groupService.CreateExpenseInGroup(
		"G1",
		u2,
		[]*Models.User{u1, u2, u3},
		900,
		Models.UNEQUAL,
		"Dinner",
		[]float64{400, 300, 200},
		nil,
	)

	if err != nil {
		panic(err)
	}

	fmt.Println("\n--- BALANCES AFTER UNEQUAL EXPENSE ---")
	balanceService.ShowAllBalances()

	// -------------------------
	// 7. Create PERCENTAGE Expense
	// -------------------------
	_, err = groupService.CreateExpenseInGroup(
		"G1",
		u3,
		[]*Models.User{u1, u2, u3},
		1000,
		Models.PERCENTAGE,
		"Trip",
		nil,
		[]float64{50, 30, 20},
	)

	if err != nil {
		panic(err)
	}

	fmt.Println("\n--- FINAL BALANCES ---")
	balanceService.ShowAllBalances()
}
