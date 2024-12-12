package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.request.ClasseAntimicrobianoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.response.ClasseAntimicrobianoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.mapper.ClasseAnimicrobianoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.repository.ClasseAntimicrobianoRepository;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClasseAntimicrobianoService {

    private final ClasseAntimicrobianoRepository classeAntimicrobianoRepository;


    public List<ListagemCadastroResponse> list() {
        var neonatoList = classeAntimicrobianoRepository.findAll();

        return neonatoList.stream()
                .map(ClasseAnimicrobianoMapper::classeAntimicrobianoToListagemCadastroResponse)
                .toList();
    }


    public ClasseAntimicrobianoResponse load(Long id) {
        var load = classeAntimicrobianoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Classe não encontrada com ID: " + id));
        return ClasseAnimicrobianoMapper.classeAntimicrobianoToClasseAnitimicrobianoResponse(load);
    }


    public ApiResponseDTO save(ClasseAntimicrobianoRequest request) throws IOException {
        var mapped = ClasseAnimicrobianoMapper.classeAntimicrobianoRequestToClasseAntimicrobiano(request);
        var saved = classeAntimicrobianoRepository.save(mapped);
        return new ApiResponseDTO(saved.getIdClasseAntimicrobano(), "O registro foi Salvo!");
    }
}
