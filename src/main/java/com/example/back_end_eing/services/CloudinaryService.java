package com.example.back_end_eing.services;

import java.io.File;
import java.io.IOException;

public interface CloudinaryService {

    public String uploadFile(File file) throws IOException;

    public String uploadBase64(String base64Image) throws IOException;



}
