package com.roderly.microbiomelabufu.pessoa.dto.request;

import com.roderly.microbiomelabufu.arquivo.dto.request.FotoPerfilRequest;
import com.roderly.microbiomelabufu.endereco.dto.request.EnderecoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record PessoaCompletoRequest(
        Long id_pessoa,
        Long id_tipo_pessoa,
        String nome,
        String sobrenome,
        Long id_sexo,
        LocalDate data_nascimento,
        String telefone,
        String email,
        Long id_estado_civil,
        Long id_profissao,
        String cpf,
        Long id_etnia,
        Long id_escolaridade,
        Long id_religiao,
        List<EnderecoRequest> endereco,
        FotoPerfilRequest foto_perfil
) {}
