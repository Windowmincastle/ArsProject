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
            // 필요한 필드들을 업데이트
            userService.saveUser(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    @PutMapping("/{email}")
//    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserEntity updatedUser) {
//        UserEntity user = userService.getUserByEmail(email);
//        if (user != null) {
//            if (updatedUser.getEmail() != null) {
//                user.setEmail(updatedUser.getEmail());
//            }
//            if (updatedUser.getTitle() != null) {
//                user.setTitle(updatedUser.getTitle());
//            }
//            if (updatedUser.getData() != null) {
//                user.setData(updatedUser.getData());
//            }
//            // 필요한 필드들을 업데이트
//            userService.saveUser(user);
//            return ResponseEntity.ok("User updated successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/{email}")
//    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserEntity updatedUser) {
//        UserEntity user = userService.getUserByEmail(email);
//        if (user != null) {
//            user.setEmail(updatedUser.getEmail());
//            user.setTitle(updatedUser.getTitle());
//            user.setData(updatedUser.getData());
//            userService.saveUser(user);
//            return ResponseEntity.ok("User updated successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


//    @PutMapping("/{email}")
//    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserEntity updatedUser) {
//        UserEntity user = userService.getUserByEmail(email);
//        if (user != null) {
//            user.setEmail(updatedUser.getEmail());
//            user.setData(updatedUser.getData());
//            user.setTitle(updatedUser.getTitle());  // title 필드 업데이트 추가
//
//            userService.saveUser(user);
//            return ResponseEntity.ok("User updated successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PutMapping("/{email}")
//    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserEntity updatedUser) {
//        UserEntity user = userService.getUserByEmail(email);
//        if (user != null) {
//            user.setEmail(updatedUser.getEmail());
//            user.setTitle(updatedUser.getTitle());
//            user.setData(updatedUser.getData());  // data 필드 업데이트 추가
//            // 필요한 필드들을 업데이트
//            userService.saveUser(user);
//            return ResponseEntity.ok("User updated successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

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



