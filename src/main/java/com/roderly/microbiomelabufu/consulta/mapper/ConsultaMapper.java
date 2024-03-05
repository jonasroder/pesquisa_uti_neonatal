package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model.TipoInformacaoSaude;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.*;
import com.roderly.microbiomelabufu.consulta.model.*;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.usuario.service.UsuarioService;
import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaMapper {
    public static PacienteConsultaResponse pacienteToPacienteConsultaResponse(Paciente paciente) {
        LocalDate dataNascimento = paciente.getData_nascimento();
        Integer idade = (dataNascimento != null) ? Period.between(dataNascimento, LocalDate.now()).getYears() : null;

        return new PacienteConsultaResponse(
                paciente.getId_paciente(),
                paciente.getFotoPerfil() != null ? paciente.getFotoPerfil().getCaminho_arquivo() + "/" + paciente.getFotoPerfil().getId_foto_perfil() + ".jpeg" : null,
                paciente.getNome() + " " + paciente.getSobrenome(),
                idade,
                paciente.getPlanoSaude() != null ? paciente.getPlanoSaude().getDescricao() : null,
                paciente.getTelefone_1(),
                paciente.getTelefone_2()
        );
    }


    public static Consulta conusltaCompletoRequestToConsulta(ConsultaCompletoRequest request) {
        Consulta consulta = new Consulta();
        Paciente paciente = new Paciente(request.id_paciente());
        var data = request.id_consulta() == null ? LocalDateTime.now() : request.data_hora();

        consulta.setId_consulta(request.id_consulta());
        consulta.setPaciente(paciente);
        consulta.setId_tipo_consulta(request.id_tipo_consulta());
        consulta.setData_hora(data);
        consulta.setObservacoes(request.observacoes());
        consulta.setSintomas(request.sintomas());
        consulta.setMedico(UsuarioService.getUsuarioAutenticado());

        return consulta;
    }


    public static ConsultaResponse consutaToConsultaResponse(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId_consulta(),
                consulta.getId_tipo_consulta(),
                consulta.getData_hora(),
                consulta.getSintomas(),
                consulta.getObservacoes(),
                consulta.getIs_active()
        );
    }
}
