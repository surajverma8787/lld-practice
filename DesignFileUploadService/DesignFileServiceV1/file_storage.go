package DesignFileServiceV1

func UploadToStorage(fileID string) string {
	return "https://s3.amazonaws.com/bucket/" + fileID
}
