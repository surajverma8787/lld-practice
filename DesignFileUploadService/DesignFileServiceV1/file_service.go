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

	url, uploadErr := UploadToStorage(fileID, file)

	if uploadErr != nil {
		return "", uploadErr
	}

	err := s.Repo.Save(fileID, fileName, url)

	if err != nil {
		return "", err
	}

	return url, nil
}
