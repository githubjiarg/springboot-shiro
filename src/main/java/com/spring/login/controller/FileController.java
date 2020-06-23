package com.spring.login.controller;

import com.spring.login.model.FileBean;
import com.spring.login.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping(value = "file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "upload")
    public void uploadFile(FileBean fileBean, @RequestParam(value = "file",required = false) MultipartFile file){
        log.info("文件名:" + file.getOriginalFilename());
        FileInputStream inputStream = null;
        try {
            inputStream = (FileInputStream) file.getInputStream();
            fileService.upload(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( null != inputStream ) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
