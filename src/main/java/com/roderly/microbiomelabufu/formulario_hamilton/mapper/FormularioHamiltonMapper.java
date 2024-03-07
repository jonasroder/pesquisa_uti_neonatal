package com.roderly.microbiomelabufu.formulario_hamilton.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.escala_intensidade.model.EscalaIntensidade;
import com.roderly.microbiomelabufu.common.Utilitarios.DateUtil;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonReponse;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.model.FormularioHamilton;

import java.time.LocalDate;
import java.time.Period;

public class FormularioHamiltonMapper {

    public static FormularioHamilton formularioHamiltonRequestToFormularioHamilton(FormularioHamiltonRequest request){
        var formulario = new FormularioHamilton();
        var consuta = request.id_consulta() != null ? new Consulta(request.id_consulta()) : null;
        var humor_ansioso = request.id_humor_ansioso() != null ? new EscalaIntensidade(request.id_humor_ansioso()) : null;
        var tensao = request.id_tensao() != null ? new EscalaIntensidade(request.id_tensao()) : null;
        var medos = request.id_medos() != null ? new EscalaIntensidade(request.id_medos()) : null;
        var insonia = request.id_insonia() != null ? new EscalaIntensidade(request.id_insonia()) : null;
        var dificuldades_intelectuais = request.id_dificuldades_intelectuais() != null ? new EscalaIntensidade(request.id_dificuldades_intelectuais()) : null;
        var humor_deprimido = request.id_humor_deprimido() != null ? new EscalaIntensidade(request.id_humor_deprimido()) : null;
        var somatizacoes_motoras = request.id_somatizacoes_motoras() != null ? new EscalaIntensidade(request.id_somatizacoes_motoras()) : null;
        var somatizacoes_sensoriais = request.id_somatizacoes_sensoriais() != null ? new EscalaIntensidade(request.id_somatizacoes_sensoriais()) : null;
        var sintomas_cardiovasculares = request.id_sintomas_cardiovasculares() != null ? new EscalaIntensidade(request.id_sintomas_cardiovasculares()) : null;
        var sintomas_respiratorios = request.id_sintomas_respiratorios() != null ? new EscalaIntensidade(request.id_sintomas_respiratorios()) : null;
        var sintomas_gastrointestinais = request.id_sintomas_gastrointestinais() != null ? new EscalaIntensidade(request.id_sintomas_gastrointestinais()) : null;
        var sintomas_geniturinarios = request.id_sintomas_geniturinarios() != null ? new EscalaIntensidade(request.id_sintomas_geniturinarios()) : null;
        var sintomas_neurovegetativos = request.id_sintomas_neurovegetativos() != null ? new EscalaIntensidade(request.id_sintomas_neurovegetativos()) : null;
        var comportamento_entrevista = request.id_comportamento_entrevista() != null ? new EscalaIntensidade(request.id_comportamento_entrevista()) : null;

        formulario.setId_formulario_hamilton(request.id_formulario_hamilton());
        formulario.setConsulta(consuta);
        formulario.setHumorAnsioso(humor_ansioso);
        formulario.setTensao(tensao);
        formulario.setMedos(medos);
        formulario.setInsonia(insonia);
        formulario.setDificuldades_intelectuais(dificuldades_intelectuais);
        formulario.setHumor_deprimido(humor_deprimido);
        formulario.setSomatizacoes_motoras(somatizacoes_motoras);
        formulario.setSomatizacoes_sensoriais(somatizacoes_sensoriais);
        formulario.setSintomas_cardiovasculares(sintomas_cardiovasculares);
        formulario.setSintomas_respiratorios(sintomas_respiratorios);
        formulario.setSintomas_gastrointestinais(sintomas_gastrointestinais);
        formulario.setSintomas_geniturinarios(sintomas_geniturinarios);
        formulario.setSintomas_neurovegetativos(sintomas_neurovegetativos);
        formulario.setComportamento_entrevista(comportamento_entrevista);

        return formulario;
    }


    public static FormularioHamiltonReponse formularioHamiltonToFormularioHamiltonResponse(FormularioHamilton formulario){
        return new FormularioHamiltonReponse(
                formulario.getId_formulario_hamilton(),
                formulario.getConsulta() != null ? formulario.getConsulta().getId_consulta() : null,
                formulario.getHumorAnsioso() != null ? formulario.getHumorAnsioso().getId_intensidade() : null,
                formulario.getTensao() != null ? formulario.getTensao().getId_intensidade() : null,
                formulario.getMedos() != null ? formulario.getMedos().getId_intensidade() : null,
                formulario.getInsonia() != null ? formulario.getInsonia().getId_intensidade() : null,
                formulario.getDificuldades_intelectuais() != null ? formulario.getDificuldades_intelectuais().getId_intensidade() : null,
                formulario.getHumor_deprimido() != null ? formulario.getHumor_deprimido().getId_intensidade() : null,
                formulario.getSomatizacoes_motoras() != null ? formulario.getSomatizacoes_motoras().getId_intensidade() : null,
                formulario.getSomatizacoes_sensoriais() != null ? formulario.getSomatizacoes_sensoriais().getId_intensidade() : null,
                formulario.getSintomas_cardiovasculares() != null ? formulario.getSintomas_cardiovasculares().getId_intensidade() : null,
                formulario.getSintomas_respiratorios() != null ? formulario.getSintomas_respiratorios().getId_intensidade() : null,
                formulario.getSintomas_gastrointestinais() != null ? formulario.getSintomas_gastrointestinais().getId_intensidade() : null,
                formulario.getSintomas_geniturinarios() != null ? formulario.getSintomas_geniturinarios().getId_intensidade() : null,
                formulario.getSintomas_neurovegetativos() != null ? formulario.getSintomas_neurovegetativos().getId_intensidade() : null,
                formulario.getComportamento_entrevista() != null ? formulario.getComportamento_entrevista().getId_intensidade() : null
        );
    }


    public static PacienteFormularioResponse consultaToPacienteFormularioResponse(Consulta consulta){
        assert consulta.getPaciente() != null;

        var paciente = consulta.getPaciente();

        var dataNascimento = paciente.getData_nascimento();
        var idade = (dataNascimento != null) ? Period.between(dataNascimento, LocalDate.now()).getYears() : null;
        var tipo_consulta = consulta.getId_tipo_consulta() == 1 ? "Consulta" : "Retorno";

        return new PacienteFormularioResponse(
                paciente.getId_paciente(),
                paciente.getFotoPerfil() != null ? paciente.getFotoPerfil().getCaminho_arquivo() + "/" + paciente.getFotoPerfil().getId_foto_perfil() + ".jpeg" : null,
                paciente.getNome() + " " + paciente.getSobrenome(),
                idade,
                paciente.getPlanoSaude() != null ? paciente.getPlanoSaude().getDescricao() : null,
                paciente.getTelefone_1(),
                paciente.getTelefone_2(),
                DateUtil.LocalDateTimeToDateBR(consulta.getData_hora()),
                tipo_consulta
        );
    }

}
