package DesignFileServiceV2

import "database/sql"

func InitDB() *sql.DB {
	connStr := ""

	db, err := sql.Open("postgres", connStr)
	if err != nil {
		panic(err)
	}
	defer db.Close()

	return db
}
