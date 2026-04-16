package DesignFileServiceV3_With_Map___WithoutDB_

import "fmt"

type Storage interface {
	GeneratePreSignedURL(key string) (string, error)
}

type S3Storage struct{}

func (s *S3Storage) GeneratePreSignedURL(key string) (string, error) {
	// simulate signed URL
	return fmt.Sprintf("https://s3-upload-url/%s", key), nil
}
