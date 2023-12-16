package com.roderly.microbiomelabufu.arquivo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderly.microbiomelabufu.arquivo.dto.request.FotoPerfilMetadataRequest;
import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.arquivo.service.FotoPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class ArquivoMapper {
    public static Arquivo fotoPerfilRequestToArquivo(FotoPerfilMetadataRequest request, Long id_pessoa, String fileStorageLocation, Long id_arquivo) {

        FotoPerfilService fotoPerfilService = new FotoPerfilService();
        String jsonMetadados = fotoPerfilService.converterMetaDados(request);

        Arquivo arquivo = new Arquivo();
        arquivo.setId_arquivo(id_arquivo);
        arquivo.setCaminho_arquivo(fileStorageLocation + "/profile_photos");
        arquivo.setEntidade("pessoa");
        arquivo.setId_entidade(id_pessoa);
        arquivo.setId_tipo_arquivo(1L);
        arquivo.setNome_arquivo(request.name());
        arquivo.setFormato(request.type());
        arquivo.setData_upload(LocalDateTime.now());
        arquivo.setTamanho(request.size());
        arquivo.setMetadados(jsonMetadados);

        return arquivo;
    }

}
