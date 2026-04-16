package DesignFileServiceV1

import "database/sql"

type FileRepository struct {
	DB *sql.DB
}

func (r *FileRepository) Save(id string, name string, url string) error {
	query := `INSERT INTO FILES (id, file_name, storage_url) VALUES ($1, $2, $3)`

	_, err := r.DB.Exec(query, id, name, url)
	return err
}
