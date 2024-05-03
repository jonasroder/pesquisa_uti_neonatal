package com.roderly.pesquisaneonatos.menu.service;

import com.roderly.pesquisaneonatos.menu.dto.response.MenuLateralResponse;
import com.roderly.pesquisaneonatos.menu.dto.response.VueRouterResponse;
import com.roderly.pesquisaneonatos.menu.mapper.MenuMapper;
import com.roderly.pesquisaneonatos.menu.mapper.VueRouterMapper;
import com.roderly.pesquisaneonatos.menu.repository.MenuRepository;
import com.roderly.pesquisaneonatos.menu.repository.VueRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
