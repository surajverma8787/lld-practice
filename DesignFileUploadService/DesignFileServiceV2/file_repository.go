package DesignFileServiceV2

import "database/sql"

type FileRepository struct {
	db *sql.DB
}

func (r *FileRepository) Save(id string, fileName string, status string, key string) error {
	query := `INSERT INTO files (id, file_name, status, storage_key) VALUES ($1, $2, $3, $4)`

	_, err := r.db.Exec(query, id, fileName, status, key)
	return err
}

func (r *FileRepository) UpdateStatus(id, status string) error {
	query := `UPDATE files SET status = $1 where id = $2`

	_, err := r.db.Exec(query, status, id)
	return err
}
