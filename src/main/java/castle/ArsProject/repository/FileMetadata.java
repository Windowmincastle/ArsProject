package castle.ArsProject.repository;

import java.io.File;

public class FileMetadata {
    private Long id;
    private String originalFilename;
    private String savedFilename;
    private String contentType;
    private String filePath; // new field to store the file path

    public FileMetadata(String originalFilename, String savedFilename, String contentType, String filePath, Long userId) {
        this.originalFilename = originalFilename;
        this.savedFilename = savedFilename;
        this.contentType = contentType;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getSavedFilename() {
        return savedFilename;
    }

    public void setSavedFilename(String savedFilename) {
        this.savedFilename = savedFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getFile() {
        return new File(filePath);
    }
}
