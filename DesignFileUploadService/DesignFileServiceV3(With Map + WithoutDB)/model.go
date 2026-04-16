package DesignFileServiceV3_With_Map___WithoutDB_

type FileStatus string

const (
	PENDING  FileStatus = "PENDING"
	UPLOADED FileStatus = "UPLOADED"
	FAILED   FileStatus = "FAILED"
)

type File struct {
	ID         string
	FileName   string
	Status     FileStatus
	StorageKey string
}

func (f *File) GetID() string {
	return f.ID
}
