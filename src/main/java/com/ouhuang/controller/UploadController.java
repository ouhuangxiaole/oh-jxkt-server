package com.ouhuang.controller;

import com.ouhuang.entity.PostImage;
import com.ouhuang.service.UploadService;
import com.ouhuang.utils.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/avatar")
    public HttpResp uploadAvatar(MultipartFile avatar) throws IOException {
        log.info("上传头像: {}", avatar.getOriginalFilename());
        String url = uploadService.uploadAvatar(avatar);
        return HttpResp.success("头像上传成功", url);
    }

    @PostMapping("/images")
    public HttpResp uploadImages(List<MultipartFile> images) throws IOException {
        if (images == null)
            return HttpResp.fail("无图片");
        log.info("上传图片, 数量: {}", images.size());
        List<String> urls = uploadService.uploadImages(images);
        return HttpResp.success("图片上传成功", urls);
    }
}
