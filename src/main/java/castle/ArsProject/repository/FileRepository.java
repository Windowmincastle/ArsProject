package castle.ArsProject.repository;

import java.util.List;

public interface FileRepository {
    FileMetadata save(FileMetadata metadata);

    FileMetadata findById(Long id);

    List<FileMetadata> findAll();

}
