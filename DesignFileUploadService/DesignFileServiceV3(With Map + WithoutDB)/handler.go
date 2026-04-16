package DesignFileServiceV3_With_Map___WithoutDB_

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type FileHandler struct {
	Service *FileService
}

func (h *FileHandler) GetUploadUrl(c *gin.Context) {
	var body struct {
		FileName string `json:"file_name"`
	}

	if err := c.BindJSON(&body); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	fileID, url, err := h.Service.GenerateUploadURL(body.FileName)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"fileId":    fileID,
		"uploadUrl": url,
	})
}

func (h *FileHandler) ConfirmUpload(c *gin.Context) {
	id := c.Param("id")

	if id == "" {
		c.JSON(http.StatusBadRequest, gin.H{"error": "id required"})
		return
	}

	if err := h.Service.ConfirmUpload(id); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"status": "uploaded"})
}
