package DesignFileServiceV3ForLargerFiles_Using_Map_

import (
	"errors"
	"sync"
)

type UploadRepository struct {
	store map[string]*MultipartUpload
	mu    sync.RWMutex
}

func NewUploadRepository() *UploadRepository {
	return &UploadRepository{
		store: make(map[string]*MultipartUpload),
	}
}

func (r *UploadRepository) Save(upload *MultipartUpload) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	if _, exists := r.store[upload.UploadID]; exists {
		return errors.New("upload already exists")
	}

	r.store[upload.UploadID] = upload
	return nil
}

func (r *UploadRepository) Get(uploadID string) (*MultipartUpload, error) {
	r.mu.RLock()
	defer r.mu.RUnlock()

	u, ok := r.store[uploadID]
	if !ok {
		return nil, errors.New("upload not found")
	}
	return u, nil
}

func (r *UploadRepository) SavePart(uploadID string, partNumber int, etag string) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	u, ok := r.store[uploadID]
	if !ok {
		return errors.New("upload not found")
	}

	u.Parts[partNumber] = etag
	return nil
}

func (r *UploadRepository) Complete(uploadID string) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	u, ok := r.store[uploadID]
	if !ok {
		return errors.New("upload not found")
	}

	u.Status = UPLOAD_COMPLETED
	return nil
}
