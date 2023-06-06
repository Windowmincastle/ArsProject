package castle.ArsProject.controller;

import castle.ArsProject.entity.UserEntity;
import castle.ArsProject.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
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
        List<Map<String, String>> data = (List<Map<String, String>>) requestData.get("data");
        String title = (String) requestData.get("title");
        String email = (String) requestData.get("email");

        UserEntity userEntity = new UserEntity();
        userEntity.setData(new Gson().toJson(data)); // Convert the data list to JSON string
        userEntity.setTitle(title);
        userEntity.setEmail(email);

        userService.saveData(userEntity);
        return ResponseEntity.ok("Data saved successfully");
    }

//-----------------------------------------------

    @GetMapping("/{email}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String email) {
        UserEntity user = userService.getUserByEmail(email);
        if (user != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
            List<Map<String, String>> data = gson.fromJson(user.getData(), type);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("data", data);
            responseData.put("title", user.getTitle());
            responseData.put("email", user.getEmail());

            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//-------------------------------------------------

    @PutMapping("/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody Map<String, Object> requestData) {
        UserEntity user = (UserEntity) userService.getUserByEmail(email);
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
        UserEntity user = (UserEntity) userService.getUserByEmail(email);
        if (user != null) {
            userService.deleteUser(user);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}



