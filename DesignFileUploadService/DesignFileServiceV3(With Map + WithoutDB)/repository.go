package DesignFileServiceV3_With_Map___WithoutDB_

import (
	"errors"
	"sync"
)

type FileRepository struct {
	store map[string]*File
	mu    sync.RWMutex
}

func NewFileRepository() *FileRepository {
	return &FileRepository{
		store: make(map[string]*File),
	}
}

func (r *FileRepository) Save(file *File) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	if _, exists := r.store[file.GetID()]; exists {
		return errors.New("file already exists")
	}

	r.store[file.GetID()] = file
	return nil
}

func (r *FileRepository) Get(id string) (*File, error) {
	r.mu.RLock()
	defer r.mu.RUnlock()

	file, exists := r.store[id]
	if !exists {
		return nil, errors.New("file not found")
	}

	return file, nil
}

func (r *FileRepository) UpdateStatus(id string, status FileStatus) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	file, exists := r.store[id]
	if !exists {
		return errors.New("file not found for updating status")
	}

	file.Status = status
	return nil
}
