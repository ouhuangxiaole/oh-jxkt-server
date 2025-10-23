package com.ouhuang.service.impl;

import com.ouhuang.entity.PostImage;
import com.ouhuang.mapper.ImageMapper;
import com.ouhuang.mapper.PostMapper;
import com.ouhuang.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    PostMapper postMapper;

    // 静态资源服务器地址 路径
    private final String STATIC_SERVER_URL = "http://localhost:3000/";
    private final String STATIC_SERVER_PATH = "D:/Code/H5/General-Server/public/";

    // 头像在服务器的目录 头像地址
    private final String AVATAR_DIR = "images/avatar/";
    private final String AVATAR_URL = STATIC_SERVER_URL + AVATAR_DIR;
    private final String AVATAR_SAVE_PATH = STATIC_SERVER_PATH + AVATAR_DIR;

    // 图片在服务器的目录 地址
    private final String IMAGE_DIR = "images/";
    private final String IMAGE_URL = STATIC_SERVER_URL + IMAGE_DIR;
    private final String IMAGE_SAVE_PATH = STATIC_SERVER_PATH + IMAGE_DIR;
    @Autowired
    private ImageMapper imageMapper;

    /**
     * 保存单一文件
     * @param file 文件内容
     * @param savePath 保存路径
     * @param url 生成的url
     * @return 返回值 文件url
     * @throws IOException
     */
    private String save(MultipartFile file, String savePath, String url) throws IOException {
        // 获取原始文件名及后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = file.getOriginalFilename().substring(originalFilename.lastIndexOf("."));
        // 生成唯一文件名
        String filename = UUID.randomUUID() + "-" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) +
                suffix.substring(suffix.lastIndexOf("."));
        // 保存文件
        file.transferTo(new File(savePath + filename));
        return url + filename;
    }

    @Override
    public String uploadAvatar(MultipartFile avatar) throws IOException {
        return save(avatar, AVATAR_SAVE_PATH, AVATAR_URL);
    }

    @Override
    public List<String> uploadImages(List<MultipartFile> images) throws IOException {
        return images.stream().map(file -> {
            try {
                return save(file, IMAGE_SAVE_PATH, IMAGE_URL);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
