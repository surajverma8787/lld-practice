package DesignFileServiceV1

import "github.com/gin-gonic/gin"

func main() {
	dbConn := InitDB()

	defer dbConn.Close()
	repo := &FileRepository{dbConn}
	svc := &FileService{Repo: repo}
	h := &FileHandler{Service: svc}

	r := gin.Default()
	r.POST("/upload", h.Upload)

	r.Run(":8080")
}
