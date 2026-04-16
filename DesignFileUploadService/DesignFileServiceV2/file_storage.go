package DesignFileServiceV2

import "fmt"

type S3Storage struct{}

func (s *S3Storage) GeneratePreSignedURL(fileID string) string {
	return fmt.Sprintf("https://s3-upload-url/%s", fileID)
}
