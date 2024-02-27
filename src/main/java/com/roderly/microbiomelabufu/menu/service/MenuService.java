package com.roderly.microbiomelabufu.menu.service;

import com.roderly.microbiomelabufu.menu.dto.response.MenuLateralResponse;
import com.roderly.microbiomelabufu.menu.dto.response.VueRouterResponse;
import com.roderly.microbiomelabufu.menu.mapper.MenuMapper;
import com.roderly.microbiomelabufu.menu.mapper.VueRouterMapper;
import com.roderly.microbiomelabufu.menu.repository.MenuRepository;
import com.roderly.microbiomelabufu.menu.repository.VueRouteRepository;
import com.roderly.microbiomelabufu.paciente.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.FotoPerfilResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.paciente.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.paciente.mapper.FotoPerfilMapper;
import com.roderly.microbiomelabufu.paciente.mapper.PacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    VueRouteRepository vueRouteRepository;

    public List<MenuLateralResponse> buscarDadosMenu() {
        var menu = menuRepository.findAll();
        return menu.stream()
                .map(MenuMapper::menuLateralToMenuLateralResponse).toList();
    }


    public List<VueRouterResponse> buscarDadosRouter(){
        var vueRoutes = vueRouteRepository.findAll();
        return VueRouterMapper.vueRouterToVueRouterResponse(vueRoutes);
    }


}
