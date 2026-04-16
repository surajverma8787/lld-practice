package DesignFileServiceV3ForLargerFiles_Using_Map_

import "fmt"

type Storage interface {
	CreateMultipartUpload(key string) (string, error)
	GeneratePartUploadURL(uploadID string, partNumber int) (string, error)
	CompleteMultipartUpload(uploadID string, parts map[int]string) error
}

type S3Storage struct{}

func (s *S3Storage) CreateMultipartUpload(key string) (string, error) {
	return "upload-" + key, nil
}

func (s *S3Storage) GeneratePartUploadURL(uploadID string, partNumber int) (string, error) {
	return fmt.Sprintf("https://s3-part-upload/%s/part/%d", uploadID, partNumber), nil
}

func (s *S3Storage) CompleteMultipartUpload(uploadID string, parts map[int]string) error {
	return nil
}
