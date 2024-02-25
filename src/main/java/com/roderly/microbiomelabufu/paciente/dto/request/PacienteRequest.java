package com.roderly.microbiomelabufu.paciente.dto.request;

import java.time.LocalDate;

public record PacienteRequest(
        Long id_paciente,
        String nome,
        String sobrenome,
        LocalDate data_nascimento,
        String telefone_1,
        String telefone_2,
        String email,
        String cpf,
        String naturalidade,
        String nome_pai,
        String nome_mae,
        String nome_conjuge,
        String numero_plano_saude,
        Long num_visitas,
        String indicacao,
        String observacao,
        Long id_profissao,
        Long id_plano_saude,
        Long id_estado_civil,
        Long id_sexo,
        Long id_etnia,
        Long id_escolaridade,
        Long id_religiao
) {}
