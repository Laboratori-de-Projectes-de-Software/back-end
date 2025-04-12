package com.example.back_end_eing.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.back_end_eing.services.CloudinaryService;
import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(@Value("${CLOUDINARY_URL}") String cloudinaryUrl) {
        this.cloudinary = new Cloudinary(cloudinaryUrl);
    }

    public String uploadFile(File file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url"); // Devuelve el URL de la imagen
    }
    public String uploadBase64(String base64Image) throws IOException {
        // Decodificar la imagen Base64
        String base64Data = base64Image.split(",")[1]; // Ignorar el encabezado Base64
        byte[] decodedBytes = Base64.decodeBase64(base64Data);

        // Crear un archivo temporal
        File tempFile = File.createTempFile("upload", ".png");

        // Escribir los bytes decodificados en el archivo temporal
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(decodedBytes);
        }

        // Subir el archivo a Cloudinary
        String url = uploadFile(tempFile);

        // Eliminar el archivo temporal
        tempFile.delete();

        // Retornar la URL segura de Cloudinary
        return url;
    }


    
}
