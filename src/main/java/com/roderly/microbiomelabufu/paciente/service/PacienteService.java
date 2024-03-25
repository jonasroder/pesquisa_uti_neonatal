package com.roderly.microbiomelabufu.paciente.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.infra.FileStorageProperties;
import com.roderly.microbiomelabufu.paciente.dto.request.PacienteCompletoRequest;
import com.roderly.microbiomelabufu.paciente.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.FotoPerfilResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteListagemResponse;
import com.roderly.microbiomelabufu.paciente.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.paciente.mapper.FotoPerfilMapper;
import com.roderly.microbiomelabufu.paciente.mapper.PacienteMapper;
import com.roderly.microbiomelabufu.paciente.model.Endereco;
import com.roderly.microbiomelabufu.paciente.model.FotoPerfil;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.paciente.repository.EnderecoRepository;
import com.roderly.microbiomelabufu.paciente.repository.FotoPerfilRepository;
import com.roderly.microbiomelabufu.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final FileStorageProperties fileStorageLocation;
    private final PacienteRepository pacienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final FotoPerfilRepository fotoPerfilRepository;

    @Transactional
    public ApiResponseDTO savePaciente(PacienteCompletoRequest request) throws IOException {

        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(request.pacienteRequest().cpf());
        if (pacienteExistente.isPresent() && request.pacienteRequest().id_paciente() == null) {
            return new ApiResponseDTO(null, "CPF já cadastrado.");
        }

        Paciente paciente = PacienteMapper.PacienteRequestToPaciente(request.pacienteRequest());
        Paciente pacienteSalvo = pacienteRepository.save(paciente);

        if (request.enderecoRequest() != null) {
            Endereco endereco = EnderecoMapper.enderecoRequestToEndereco(request.enderecoRequest(), pacienteSalvo);
            enderecoRepository.save(endereco);
        }

        if (request.fotoPerfilRequest() != null) {
            FotoPerfil fotoPerfil = FotoPerfilMapper.fotoPerfilMetadataRequestToFotoPerfil(request.fotoPerfilRequest().metadata(), pacienteSalvo, null);
            if (request.pacienteRequest().id_paciente() != null) {
                Optional<Long> fotoPerfilIdOptional = fotoPerfilRepository.findIdFotoPerfilByIdPaciente(request.pacienteRequest().id_paciente());
                fotoPerfilIdOptional.ifPresent(fotoPerfil::setId_foto_perfil);
            }
            FotoPerfil fotoPerfilSalva = fotoPerfilRepository.save(fotoPerfil);
            pacienteSalvo.setFotoPerfil(fotoPerfilSalva);
            saveBase64ToFile(request.fotoPerfilRequest().base64(), fotoPerfilSalva.getId_foto_perfil());
        }

        return new ApiResponseDTO((long) pacienteSalvo.getId_paciente(), "O Paciente foi Salvo!");
    }


    public void saveBase64ToFile(String base64String, Long insertId) throws IOException {

        String base64Image = base64String.split(",")[1];
        byte[] data = Base64.getDecoder().decode(base64Image);

        Path directoryPath = Paths.get(fileStorageLocation.getFileStorageLocation(), "profile_photos");
        Files.createDirectories(directoryPath);
        Path fileFullPath = directoryPath.resolve(insertId + ".jpeg");

        try (FileOutputStream fos = new FileOutputStream(fileFullPath.toFile())) {
            fos.write(data);
        }
    }


    public PacienteCompletoResponse getDadosPaciente(Long id_paciente) {
        var paciente = pacienteRepository.findById(id_paciente).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrada com ID: " + id_paciente));
        var pacienteResponse = PacienteMapper.pacienteToPacienteResponse(paciente);

        EnderecoResponse enderecoResponse = null;
        FotoPerfilResponse fotoPerfilResponse = null;

        if (paciente.getEndereco() != null) {
             enderecoResponse = EnderecoMapper.enderecoToEnderecoResponse(paciente.getEndereco());
        }

        if (paciente.getFotoPerfil() != null) {
             fotoPerfilResponse = FotoPerfilMapper.fotoPerfilToFotoPerfilResponse(paciente);
        }

        return new PacienteCompletoResponse(pacienteResponse, enderecoResponse, fotoPerfilResponse);
    }


    public Set<PacienteListagemResponse> getDadosListagemPaciente() {
        var pacientes = pacienteRepository.findAll();

        return pacientes.stream()
                .map(PacienteMapper::pacienteToPacienteListagemResponse)
                .collect(Collectors.toSet());
    }
}
