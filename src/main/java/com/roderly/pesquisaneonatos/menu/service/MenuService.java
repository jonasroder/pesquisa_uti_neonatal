package com.roderly.pesquisaneonatos.menu.service;

import com.roderly.pesquisaneonatos.common.persistense.EntitySpecs;
import com.roderly.pesquisaneonatos.menu.dto.response.MenuLateralResponse;
import com.roderly.pesquisaneonatos.menu.dto.response.VueRouterResponse;
import com.roderly.pesquisaneonatos.menu.mapper.MenuMapper;
import com.roderly.pesquisaneonatos.menu.mapper.VueRouterMapper;
import com.roderly.pesquisaneonatos.menu.repository.MenuRepository;
import com.roderly.pesquisaneonatos.menu.repository.VueRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService {

    private  final MenuRepository menuRepository;
    private  final VueRouteRepository vueRouteRepository;

    public List<MenuLateralResponse> buscarDadosMenu() {
        var menu =  menuRepository.findAll(EntitySpecs.active(), EntitySpecs.orderBy("ASC", "ordem"));

        return menu.stream()
                .map(MenuMapper::menuLateralToMenuLateralResponse)
                .toList();
    }


    public List<VueRouterResponse> buscarDadosRouter(){
        var vueRoutes = vueRouteRepository.findAll();
        return VueRouterMapper.vueRouterToVueRouterResponse(vueRoutes);
    }


}
