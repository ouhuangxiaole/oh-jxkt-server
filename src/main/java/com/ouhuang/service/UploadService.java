package com.ouhuang.service;

import com.ouhuang.entity.PostImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UploadService {
    String uploadAvatar(MultipartFile avatar) throws IOException;

    List<String> uploadImages(List<MultipartFile> images) throws IOException;
}
