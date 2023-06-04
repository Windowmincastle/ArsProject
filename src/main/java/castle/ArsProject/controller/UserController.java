package castle.ArsProject.controller;

import castle.ArsProject.entity.UserEntity;
import castle.ArsProject.service.UserService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> saveData(@RequestBody Map<String, Object> requestData) {
        String data = requestData.get("data").toString();
        String title = (String) requestData.get("title");
        String email = (String) requestData.get("email");

        UserEntity userEntity = new UserEntity();
        userEntity.setData(data);
        userEntity.setTitle(title);
        userEntity.setEmail(email);

        userService.saveData(userEntity);
        return ResponseEntity.ok("Data saved successfully");
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String email) {
        UserEntity user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody Map<String, Object> requestData) {
        UserEntity user = userService.getUserByEmail(email);
        if (user != null) {
            if (requestData.containsKey("email")) {
                user.setEmail(requestData.get("email").toString());
            }
            if (requestData.containsKey("title")) {
                user.setTitle(requestData.get("title").toString());
            }
            if (requestData.containsKey("data")) {
                user.setData(requestData.get("data").toString());
            }

            userService.saveUser(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        UserEntity user = userService.getUserByEmail(email);
        if (user != null) {
            userService.deleteUser(user);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}



