package castle.ArsProject.service;

import castle.ArsProject.entity.UserEntity;
import castle.ArsProject.repository.DataRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final DataRepository dataRepository;

    public UserService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveData(UserEntity userEntity) {
        dataRepository.save(userEntity);
    }
}




