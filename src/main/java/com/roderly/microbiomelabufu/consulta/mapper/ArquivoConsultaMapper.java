package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.tipo_arquivo.model.TipoArquivo;
import com.roderly.microbiomelabufu.common.Utilitarios.DateUtil;
import com.roderly.microbiomelabufu.consulta.dto.request.ArquivoConsultaRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.ArquivoConsultaReponse;
import com.roderly.microbiomelabufu.consulta.model.ArquivoConsulta;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ArquivoConsultaMapper {

    public static ArquivoConsulta uploadToArquivoConsulta(Long id_consulta, Long id_tipo_arquivo, MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = DateUtil.extractFileExtension(originalFileName);

        var arquivoConsulta = new ArquivoConsulta();
        var tipoArquivo = new TipoArquivo(id_tipo_arquivo);
        var consulta = new Consulta(id_consulta);

        arquivoConsulta.setConsulta(consulta);
        arquivoConsulta.setTipoArquivo(tipoArquivo);
        arquivoConsulta.setNome_arquivo(file.getOriginalFilename());
        arquivoConsulta.setCaminho_arquivo("/uploads/arquivos_consulta");
        arquivoConsulta.setTamanho(file.getSize());
        arquivoConsulta.setFormato(fileExtension);
        arquivoConsulta.setData_upload(LocalDateTime.now());
        arquivoConsulta.setIs_active(true);

        return arquivoConsulta;
    }


    public static Set<ArquivoConsultaReponse> arquivoConsultaToArquivoConsultaResponse(Set<ArquivoConsulta> arquivo) {
        return arquivo.stream().map(arquivoConsultaResponse -> {
            return new ArquivoConsultaReponse(
                    arquivoConsultaResponse.getId_arquivo(),
                    arquivoConsultaResponse.getNome_arquivo(),
                    DateUtil.LocalDateTimeToDateBR(arquivoConsultaResponse.getData_upload()),
                    arquivoConsultaResponse.getTipoArquivo().getDescricao(),
                    arquivoConsultaResponse.getIs_active()
            );
        }).collect(Collectors.toSet());
    }


    public static ArquivoConsulta arquivoConsultaRequestToArquivoConsulta (ArquivoConsultaRequest request, ArquivoConsulta arquivoConsulta){
        arquivoConsulta.setIs_active(request.is_active());
        return arquivoConsulta;
    }
}
