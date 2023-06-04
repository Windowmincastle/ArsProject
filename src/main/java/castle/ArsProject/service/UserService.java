package castle.ArsProject.service;

import castle.ArsProject.entity.UserEntity;
import castle.ArsProject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    public void saveData(UserEntity userEntity) {
        userRepository.save(userEntity);
    }


//    public void saveData(UserEntity userEntity) {
//        UserRepository.save(userEntity);
//    }

}




