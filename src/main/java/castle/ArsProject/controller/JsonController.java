package castle.ArsProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.File;
import java.util.*;


@RestController
public class JsonController {

    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<String> valueList = new ArrayList<>(); // 인스턴스 변수로 변경

    @GetMapping("/jsonget")
    public ResponseEntity<List<Map<String, Object>>> getData(@RequestParam String job, @RequestParam String gender, @RequestParam String userid) {

        List<Map<String, Object>> resultList = new ArrayList<>();
        readJsonFile(userid + ".json");

        String[] parts = {"hair", "cap", "face", "neck", "coat", "belt", "pants", "shoes"};//, "skin"
        String[] sections = {"머리", "모자", "얼굴", "목가슴", "상의", "허리", "하의", "신발"};//, "피부"
        String[] itemNames = valueList.toArray(new String[0]);

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            String section = sections[i];
            String itemName = itemNames[i];

            String sql =
                    "SELECT " + gender + job + "_itemname." + part + ", " + gender + job + "_index." + part + "index, " + gender + job + "_icon." + part + "icon" +
                            " FROM " + gender + job + "_itemname" +
                            " INNER JOIN " + gender + job + "_index ON " + gender + job + "_itemname.id = " + gender + job + "_index.id" +
                            " INNER JOIN " + gender + job + "_icon ON " + gender + job + "_itemname.id = " + gender + job + "_icon.id" +
                            " INNER JOIN " + gender + job + "_position ON " + gender + job + "_itemname.id = " + gender + job + "_position.id" +
                            " WHERE " + gender + job + "_position." + part + "position = " + itemName + " LIMIT 1;";

            Map<String, Object> itemResultMap = jdbcTemplate.queryForMap(sql);
            Map<String, Object> itemMap = new HashMap<>();

            itemMap.put("index", itemResultMap.get(part + "index"));
            itemMap.put("icon", itemResultMap.get(part + "icon"));
            itemMap.put("name", itemResultMap.get(part));
            itemMap.put("part", section);
            resultList.add(itemMap);

        }

        // JSON 데이터 초기화
        valueList.clear();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    private void readJsonFile(String fileName) {
        try {

            String filePath = uploadDir + "/" + fileName;
            File file = new File(filePath);

            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, String>> jsonList = objectMapper.readValue(file, new TypeReference<List<Map<String, String>>>() {
            });


            valueList.clear();
            for (Map<String, String> jsonMap : jsonList) {
                for (String value : jsonMap.values()) {
                    valueList.add(value);
                }
            }

            System.out.println("받아온 JSON 데이터 =\n" + valueList);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
