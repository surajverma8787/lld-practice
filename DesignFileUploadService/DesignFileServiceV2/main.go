package DesignFileServiceV2

import "github.com/gin-gonic/gin"

func main() {
	dbConn := InitDB()
	repo := &FileRepository{db: dbConn}
	storage := &S3Storage{}

	svc := &FileService{
		Repo:    repo,
		Storage: storage,
	}

	h := &FileHandler{Service: svc}

	r := gin.Default()

	r.POST("/upload-url", h.GetUploadURL)
	r.POST("/confirm/:id", h.ConfirmUpload)

	r.Run(":8080")
}
