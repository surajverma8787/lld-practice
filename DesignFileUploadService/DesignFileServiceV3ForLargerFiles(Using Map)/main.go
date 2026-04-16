package DesignFileServiceV3ForLargerFiles_Using_Map_

import "github.com/gin-gonic/gin"

func main() {

	repo := NewUploadRepository()
	storage := &S3Storage{}

	service := &UploadService{
		Repo:    repo,
		Storage: storage,
	}

	handler := &UploadHandler{Service: service}

	r := gin.Default()

	r.POST("/start", handler.Start)
	r.GET("/upload/:id/part", handler.GetPartURL)
	r.POST("/upload/:id/part", handler.SavePart)
	r.POST("/upload/:id/complete", handler.Complete)

	r.Run(":8080")
}
