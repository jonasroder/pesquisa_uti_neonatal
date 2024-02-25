package com.roderly.microbiomelabufu.paciente.mapper;

import com.roderly.microbiomelabufu.paciente.dto.request.FotoPerfilMetadataRequest;
import com.roderly.microbiomelabufu.paciente.dto.response.FotoPerfilResponse;
import com.roderly.microbiomelabufu.paciente.model.FotoPerfil;
import com.roderly.microbiomelabufu.paciente.model.Paciente;

import java.time.LocalDateTime;
import java.util.Optional;

public class FotoPerfilMapper {

    public static FotoPerfil fotoPerfilMetadataRequestToFotoPerfil(FotoPerfilMetadataRequest request, Paciente paciente, Long id_foto_perfil){
        FotoPerfil fotoPerfil = new FotoPerfil();

        fotoPerfil.setId_foto_perfil(id_foto_perfil);
        fotoPerfil.setNome_arquivo(request.name());
        fotoPerfil.setCaminho_arquivo("/uploads/profile_photos");
        fotoPerfil.setTamanho(request.size());
        fotoPerfil.setFormato(request.type());
        fotoPerfil.setData_upload(LocalDateTime.now());
        fotoPerfil.setPaciente(paciente);

        return fotoPerfil;
    }

    public  static FotoPerfilResponse fotoPerfilToFotoPerfilResponse(Paciente paciente){
        return new FotoPerfilResponse(
                paciente.getFotoPerfil() != null ? paciente.getFotoPerfil().getCaminho_arquivo() + "/" + paciente.getFotoPerfil().getId_foto_perfil() + ".jpeg" : null
        );
    }
}
