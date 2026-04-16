package DesignFileServiceV1

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type FileHandler struct {
	Service *FileService
}

func (h *FileHandler) Upload(c *gin.Context) {
	file, err := c.FormFile("file")

	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
	}

	f, err := file.Open()
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
	}
	defer f.Close()

	url, err := h.Service.UploadFile(f, file.Filename)
	if err != nil {
		c.JSON(http.StatusInternalServerError, err.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"url": url,
	})
}
