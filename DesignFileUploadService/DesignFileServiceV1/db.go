package DesignFileServiceV1

import "database/sql"

func InitDB() *sql.DB {
	connStr := "postgres://user:password@localhost:5432/filedb?sslmode=disable"

	db, err := sql.Open("postgres", connStr)

	if err != nil {
		panic(err)
	}

	return db
}
