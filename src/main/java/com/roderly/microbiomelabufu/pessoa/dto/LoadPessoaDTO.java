package com.roderly.microbiomelabufu.pessoa.dto;

import com.roderly.microbiomelabufu.endereco.dto.LoadEnderecoDTO;
import com.roderly.microbiomelabufu.pessoa.model.PessoaModel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record LoadPessoaDTO(
        Long id_pessoa,
        String nome,
        String sobrenome,
        LocalDate data_nascimento,
        String telefone,
        String email,
        Integer id_estado_civil,
        Integer id_profissao,
        String cpf,
        Integer id_etnia,
        Integer id_escolaridade,
        Integer id_religiao,
        Integer id_sexo,
        Integer id_tipo_pessoa,
        List<LoadEnderecoDTO> endereco
) {
    public static LoadPessoaDTO fromPessoaModel(PessoaModel pessoaModel) {

        return new LoadPessoaDTO(
                pessoaModel.getId_pessoa(),
                pessoaModel.getNome(),
                pessoaModel.getSobrenome(),
                pessoaModel.getData_nascimento(),
                pessoaModel.getTelefone(),
                pessoaModel.getEmail(),
                pessoaModel.getId_estado_civil(),
                pessoaModel.getId_profissao(),
                pessoaModel.getCpf(),
                pessoaModel.getId_etnia(),
                pessoaModel.getId_escolaridade(),
                pessoaModel.getId_religiao(),
                pessoaModel.getId_sexo(),
                pessoaModel.getId_tipo_pessoa(),
                pessoaModel.getEnderecos().stream()
                        .map(LoadEnderecoDTO::new) // Mantendo o mapeamento para LoadEnderecoDTO
                        .collect(Collectors.toList())
        );
    }
}
