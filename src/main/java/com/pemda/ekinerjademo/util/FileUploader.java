package com.pemda.ekinerjademo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by bagus on 13/12/17.
 */
public class FileUploader {

    public void uploadSuratLembarDisposisi(MultipartFile fileSurat, String fileName) {
        byte[] bytes = null;

        try {
            bytes = fileSurat.getBytes();

            Path path
                    = Paths.get("/home/pemkab/project/documents/lembar_disposisi/"
                    +fileName+"."
                    +fileSurat.getOriginalFilename().split("\\.")[1]);


            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
