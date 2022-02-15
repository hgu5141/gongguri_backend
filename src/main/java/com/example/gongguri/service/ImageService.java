package com.example.gongguri.service;

import com.example.gongguri.dto.ImageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
    public ImageDto imageUpload(MultipartFile file) throws IOException {
        String path = "/image/";
        String saveLocation = "/home/ubuntu/image/";
        System.out.println(file);
//        String saveLocation = "/Users/jeong-yeongbin/Desktop/project/Team-11-Back/src/main/resources/static/image/";

        // 같은 이름의 이미지 파일을 방지하고자 램덤함 UUID를 생성해서 파일이름앞에 붙힌다.
        UUID uuid = UUID.randomUUID();
        String originFileName = file.getOriginalFilename();

        originFileName = originFileName.replace(" .", ".");
        String fileName = uuid + "_" + originFileName;
        file.transferTo(new File(saveLocation + fileName));
        path += fileName;
        path = path.replace(" .", ".");

        System.out.println(ImageDto.builder().imageUrl(path).build());
        return ImageDto.builder().imageUrl(path).build();
    }
}
