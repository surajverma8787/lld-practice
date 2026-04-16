package DesignFileServiceV3ForLargerFiles_Using_Map_

import (
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
)

type UploadHandler struct {
	Service *UploadService
}

func (h *UploadHandler) Start(c *gin.Context) {
	var body struct {
		FileName string `json:"file_name"`
	}

	if err := c.ShouldBindJSON(&body); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	uploadID, err := h.Service.StartUpload(body.FileName)
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{"uploadId": uploadID})
}

func (h *UploadHandler) GetPartURL(c *gin.Context) {
	id := c.Param("id")
	part, _ := strconv.Atoi(c.Query("part"))

	url, err := h.Service.GetPartURL(id, part)
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{"url": url})
}

// Save Part
func (h *UploadHandler) SavePart(c *gin.Context) {
	id := c.Param("id")

	var body struct {
		Part int    `json:"part"`
		ETag string `json:"etag"`
	}

	if err := c.ShouldBindJSON(&body); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	err := h.Service.SavePart(id, body.Part, body.ETag)
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	c.JSON(http.StatusOK, "saved")
}

func (h *UploadHandler) Complete(c *gin.Context) {
	id := c.Param("id")
	err := h.Service.CompleteUpload(id)

	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	
	c.JSON(http.StatusOK, "complete")
}
