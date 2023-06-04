package castle.ArsProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import castle.ArsProject.service.FileService;
import org.springframework.ui.Model;
import java.io.IOException;
import java.util.List;

@RestController
public class FileController
{

    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("userid") String userid, Model model) {

        try {
        List<String> filenames = fileService.saveFiles(file, userid);
        model.addAttribute("filenames", filenames);
        return "성공";
        } catch (IOException e) {
        model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
        return "실패";
        }
    }

}