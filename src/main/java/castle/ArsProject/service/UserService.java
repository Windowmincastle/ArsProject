package castle.ArsProject.service;

import castle.ArsProject.entity.UserEntity;
import castle.ArsProject.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
