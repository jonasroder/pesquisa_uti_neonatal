package com.roderly.pesquisaneonatos.menu.mapper;

import com.roderly.pesquisaneonatos.menu.dto.response.VueRouterResponse;
import com.roderly.pesquisaneonatos.menu.model.VueRouter;

import java.util.List;

public class VueRouterMapper {

    public static List<VueRouterResponse> vueRouterToVueRouterResponse(List<VueRouter> vueRouterList) {
        return vueRouterList.stream()
                .map(vueRouter -> new VueRouterResponse(
                        vueRouter.getPath(),
                        vueRouter.getName(),
                        vueRouter.getComponent(),
                        vueRouter.getTitle()
                )).toList();
    }
}
