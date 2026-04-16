package DesignFileServiceV2

import "github.com/google/uuid"

type FileService struct {
	Repo    *FileRepository
	Storage *S3Storage
}

func (s *FileService) GenerateUploadURL(fileName string) (string, string, error) {
	fileID := uuid.New().String()

	url := s.Storage.GeneratePreSignedURL(fileID)

	err := s.Repo.Save(fileID, fileName, "PENDING", fileID)

	if err != nil {
		return "", "", err
	}

	return fileID, url, nil
}

func (s *FileService) ConfirmUpload(fileID string) error {
	return s.Repo.UpdateStatus(fileID, "UPLOADED")
}
