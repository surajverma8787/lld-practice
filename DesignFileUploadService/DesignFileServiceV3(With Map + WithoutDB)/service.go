package DesignFileServiceV3_With_Map___WithoutDB_

import (
	"errors"
	"fmt"

	"github.com/google/uuid"
)

type FileService struct {
	Repo    *FileRepository
	Storage Storage
}

func (s *FileService) GenerateUploadURL(fileName string) (string, string, error) {
	if fileName == "" {
		return "", "", errors.New("file name is required")
	}

	fileID := uuid.New().String()

	url, err := s.Storage.GeneratePreSignedURL(fileID)

	if err != nil {
		return "", "", fmt.Errorf("generate pre-signed url: %w", err)
	}

	file := &File{
		ID:         fileID,
		FileName:   fileName,
		Status:     PENDING,
		StorageKey: fileID,
	}

	err = s.Repo.Save(file)

	if err != nil {
		return "", "", fmt.Errorf("save file: %w", err)
	}

	return fileID, url, nil
}

func (s *FileService) ConfirmUpload(fileID string) error {
	file, err := s.Repo.Get(fileID)
	if err != nil {
		return err
	}

	if file.Status == UPLOADED {
		return nil
	}

	if file.Status != PENDING {
		return errors.New("invalid file state")
	}

	return s.Repo.UpdateStatus(fileID, UPLOADED)
}
