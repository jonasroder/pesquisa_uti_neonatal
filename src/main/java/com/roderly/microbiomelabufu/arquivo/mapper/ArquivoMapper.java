package com.roderly.microbiomelabufu.arquivo.mapper;

import com.roderly.microbiomelabufu.arquivo.dto.request.FotoPerfilMetadataRequest;
import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.arquivo.service.FotoPerfilService;

import java.time.LocalDateTime;

public class ArquivoMapper {
    public static Arquivo fotoPerfilRequestToArquivo(FotoPerfilMetadataRequest request, Long id_pessoa, String fileStorageLocation, Long id_arquivo) {

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
        arquivo.setMetadados(FotoPerfilService.converterMetaDados(request));

        return arquivo;
    }

}
