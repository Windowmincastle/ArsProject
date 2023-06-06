package castle.ArsProject.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LocalFileRepository implements FileRepository {

    private final Map<Long, FileMetadata> files = new ConcurrentHashMap<>();

    @Override
    public FileMetadata save(FileMetadata metadata) {

        Long id = generateId();


        metadata.setId(id);


        files.put(id, metadata);

        return metadata;
    }

    @Override
    public FileMetadata findById(Long id) {

        return files.get(id);
    }

    @Override
    public List<FileMetadata> findAll() {

        return new ArrayList<>(files.values());
    }

    private Long generateId() {

        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
