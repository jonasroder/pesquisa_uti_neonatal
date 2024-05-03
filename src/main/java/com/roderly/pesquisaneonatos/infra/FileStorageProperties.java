package com.roderly.pesquisaneonatos.infra;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FileStorageProperties {
    @Value("${file.storage.location}")
    private String fileStorageLocation;

}