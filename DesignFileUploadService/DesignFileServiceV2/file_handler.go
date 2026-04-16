package DesignFileServiceV2

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type FileHandler struct {
	Service *FileService
}

func (h *FileHandler) GetUploadURL(c *gin.Context) {
	var body struct {
		FileName string `json:"file_name"`
	}

	err := c.BindJSON(&body)
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	fileID, url, uploadErr := h.Service.GenerateUploadURL(body.FileName)

	if uploadErr != nil {
		c.JSON(http.StatusInternalServerError, uploadErr.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"fileID": fileID,
		"url":    url,
	})
}

func (h *FileHandler) ConfirmUpload(c *gin.Context) {
	id := c.Param("id")

	err := h.Service.ConfirmUpload(id)
	if err != nil {
		c.JSON(http.StatusInternalServerError, err.Error())
		return
	}

	c.JSON(http.StatusOK, "uploaded")
}
