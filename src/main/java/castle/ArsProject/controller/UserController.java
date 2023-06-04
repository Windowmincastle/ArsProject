package castle.ArsProject.controller;

import castle.ArsProject.entity.UserEntity;
import castle.ArsProject.service.UserService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/data")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> saveData(@RequestBody Map<String, Object> requestData) {
        String data = new Gson().toJson(requestData.get("data"));
        String title = (String) requestData.get("title");
        String email = (String) requestData.get("email");

        UserEntity userEntity = new UserEntity();
        userEntity.setData(data);
        userEntity.setTitle(title);
        userEntity.setEmail(email);

        userService.saveData(userEntity);
        return ResponseEntity.ok("Data saved successfully");
    }
}



