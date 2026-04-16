package DesignFileServiceV1

import (
	"io"
	"os"
)

func UploadToStorage(fileID string, file io.Reader) (string, error) {
	path := "./uploads/" + fileID

	out, err := os.Create(path)
	if err != nil {
		return "", err
	}
	defer out.Close()

	// copy file content
	_, err = io.Copy(out, file)
	if err != nil {
		return "", err
	}

	// simulate S3 URL
	return "https://s3.amazonaws.com/bucket/" + fileID, nil
}
