package com.roderly.pesquisaneonatos.menu.dto.response;

import java.util.List;

public record MenuLateralResponse(
        String icon,
        String title,
        String activator,
        List<SubMenuResponse> subMenus,
        VueRouterResponse vueRouter

) {
}
