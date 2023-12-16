package com.roderly.microbiomelabufu.arquivo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderly.microbiomelabufu.arquivo.dto.request.FotoPerfilMetadataRequest;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class FotoPerfilService {
    public static String converterMetaDados(FotoPerfilMetadataRequest metadados){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMetadados;
        try {
            jsonMetadados = objectMapper.writeValueAsString(metadados);
        } catch (JsonProcessingException e) {
            jsonMetadados = "{}";
        }

        return jsonMetadados;
    }


    public static void saveBase64ToFile(String base64String, String filePath, Long insertId) throws IOException {
        // Decodificar a string base64
        String base64Image = base64String.split(",")[1];
        byte[] data = Base64.getDecoder().decode(base64Image);

        // O caminho completo para a pasta onde os arquivos serão salvos
        Path directoryPath = Paths.get(filePath, "profile_photos");
        Files.createDirectories(directoryPath);

        // Construir o caminho completo do arquivo com a extensão correta
        Path fileFullPath = directoryPath.resolve(insertId + ".jpeg");

        // Escrever os dados decodificados no arquivo
        try (FileOutputStream fos = new FileOutputStream(fileFullPath.toFile())) {
            fos.write(data);
        }
    }

}
