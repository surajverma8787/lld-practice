package DesignFileServiceV3ForLargerFiles_Using_Map_

type UploadStatus string

const (
	UPLOAD_PENDING   UploadStatus = "PENDING"
	UPLOAD_COMPLETED UploadStatus = "COMPLETED"
)

type MultipartUpload struct {
	UploadID string
	FileName string
	Parts    map[int]string // partNumber → ETag
	Status   UploadStatus
}
