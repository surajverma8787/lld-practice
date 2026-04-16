package DesignFileServiceV3ForLargerFiles_Using_Map_

import (
	"errors"

	"github.com/google/uuid"
)

type UploadService struct {
	Repo    *UploadRepository
	Storage Storage
}

// Step 1: Start upload
func (s *UploadService) StartUpload(fileName string) (string, error) {
	if fileName == "" {
		return "", errors.New("file name required")
	}

	fileID := uuid.New().String()

	uploadID, err := s.Storage.CreateMultipartUpload(fileID)
	if err != nil {
		return "", err
	}

	upload := &MultipartUpload{
		UploadID: uploadID,
		FileName: fileName,
		Parts:    make(map[int]string),
		Status:   UPLOAD_PENDING,
	}

	err = s.Repo.Save(upload)

	if err != nil {
		return "", err
	}

	return uploadID, nil
}

// Step 2: Get part upload URL
func (s *UploadService) GetPartURL(uploadID string, partNumber int) (string, error) {

	if partNumber <= 0 {
		return "", errors.New("invalid part number")
	}

	_, err := s.Repo.Get(uploadID)
	if err != nil {
		return "", err
	}

	return s.Storage.GeneratePartUploadURL(uploadID, partNumber)
}

// Step 3: Save uploaded part
func (s *UploadService) SavePart(uploadID string, partNumber int, etag string) error {
	return s.Repo.SavePart(uploadID, partNumber, etag)
}

// Step 4: Complete upload
func (s *UploadService) CompleteUpload(uploadID string) error {

	u, err := s.Repo.Get(uploadID)
	if err != nil {
		return err
	}

	if len(u.Parts) == 0 {
		return errors.New("no parts uploaded")
	}

	if err := s.Storage.CompleteMultipartUpload(uploadID, u.Parts); err != nil {
		return err
	}

	return s.Repo.Complete(uploadID)
}
