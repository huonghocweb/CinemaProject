package com.poly.cinemaproject.utils;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUtils {
    @Autowired
    ServletContext app;
    String realPath = "D:\\HK_6\\Java6\\Java6_Intellij\\CinemaProject\\src\\main\\resources\\static\\images\\";
    // đưa vào 1 tên folder và tên file, sẽ trả về đường dẫn đến file tương ứng. Nếu chưa có sẽ tạo ra và trả về
    private Path getPath(String folder, String filename) {

        File dir= Paths.get(realPath,folder).toFile();
        System.out.println("Real Path: " + realPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return Paths.get(dir.getAbsolutePath(),filename);
    }


    // đưa vào 1 tên folder và tên file , sau đó gọi đến function getPath ở trên để lấy ra path. Sau đó đọc toàn bộ nội dung của tệp tin theo đường dẫn path
    // trả về tệp tin dưới dạng mảng byte nếu không trả về lỗi
    public byte[] read(String folder, String filename) {
        Path path= this.getPath(folder, filename);
        try {
            return Files.readAllBytes(path);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // đưa vào tên folder và tên file , lấy đường dẫn path và xóa nó đi
    public void delete(String folder , String filename) {
        Path path= this.getPath(folder, filename);
        path.toFile().delete();
    }

    // đưa vào tên folder và trả về cho ta 1 list là filenames của folder đó .
    public List<String > list(String folder){
        List<String > filenames= new ArrayList<>();
        File dir= Paths.get(realPath,folder).toFile();
        if(dir.exists()) {
            File[] files= dir.listFiles();
            for(File file: files) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }

    // truyền vào tên folder , và file dưới dạng multipart (dạng được gửi lên từ client) . Sau đó ghép với current time để tạo ra filename riêng biệt
    // lấy ra đường dẫn path từ getPath ở trên  sau đó lưu file vào , tiến hành thêm tên file vào list filenames để trả về
    public List<String> save(String folder, MultipartFile[] files){
        List<String> filenames= new ArrayList<>();
        for(MultipartFile file : files) {
            String name= System.currentTimeMillis()+ file.getOriginalFilename();
            String filename= Integer.toHexString(name.hashCode()) + name.substring(name.indexOf("."));
            Path path= this.getPath(folder, filename);
            try {
                file.transferTo(path);
                filenames.add(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filenames;
    }
}
