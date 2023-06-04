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
        // Generate a unique ID for the file
        Long id = generateId();

        // Set the ID on the metadata object
        metadata.setId(id);

        // Store the metadata object in the in-memory map
        files.put(id, metadata);

        return metadata;
    }

    @Override
    public FileMetadata findById(Long id) {
        // Retrieve the metadata object from the in-memory map
        return files.get(id);
    }

    @Override
    public List<FileMetadata> findAll() {
        // Return a list of all metadata objects in the in-memory map
        return new ArrayList<>(files.values());
    }

    private Long generateId() {
        // Generate a unique ID using a UUID
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
