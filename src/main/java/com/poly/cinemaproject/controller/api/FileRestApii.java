package com.poly.cinemaproject.controller.api;

import com.poly.cinemaproject.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/files")
public class FileRestApii {

    @Autowired
    FileUtils fileUtils;
    @GetMapping("/{folder}")
    public List<String> list(
            @PathVariable("folder") String folder
    ){
        return fileUtils.list(folder);
    }

    @GetMapping("/{folder}/{file}")
    public byte[] dowload(
            @PathVariable("folder") String folder,
            @PathVariable("file") String file
    ){
        return fileUtils.read(folder, file);
    }

    @DeleteMapping("/{folder}/{file}")
    public void delete(
            @PathVariable("folder") String folder,
            @PathVariable("file") String file
    ){
        fileUtils.delete(folder, file);
    }

    @PostMapping("/{folder}")
    public  List<String > upload(
            @PathVariable("folder") String folder,
            @RequestParam("image")MultipartFile[] files
            ){
        return fileUtils.save(folder, files);
    }
}
