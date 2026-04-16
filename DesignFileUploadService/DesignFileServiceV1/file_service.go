package DesignFileServiceV1

import (
	"mime/multipart"

	"github.com/google/uuid"
)

type FileService struct {
	Repo *FileRepository
}

func (s *FileService) UploadFile(file multipart.File, fileName string) (string, error) {
	fileID := uuid.New().String()

	url := UploadToStorage(fileID)

	err := s.Repo.Save(fileID, fileName, url)

	if err != nil {
		return "", err
	}

	return url, nil
}
