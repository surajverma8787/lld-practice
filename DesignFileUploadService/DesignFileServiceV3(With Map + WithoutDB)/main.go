package DesignFileServiceV3_With_Map___WithoutDB_

import "github.com/gin-gonic/gin"

func main() {
	repo := NewFileRepository()
	storage := &S3Storage{}

	service := &FileService{
		Repo:    repo,
		Storage: storage,
	}

	handler := &FileHandler{
		Service: service,
	}

	r := gin.Default()

	r.POST("/upload-url", handler.GetUploadUrl)
	r.POST("/confirm/:id", handler.ConfirmUpload)

	r.Run(":8080")
}
