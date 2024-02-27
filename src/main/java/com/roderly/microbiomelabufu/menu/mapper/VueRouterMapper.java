package com.roderly.microbiomelabufu.menu.mapper;

import com.roderly.microbiomelabufu.menu.dto.response.VueRouterResponse;
import com.roderly.microbiomelabufu.menu.model.VueRouter;

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
