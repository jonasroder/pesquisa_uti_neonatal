package com.roderly.microbiomelabufu.menu.mapper;

import com.roderly.microbiomelabufu.menu.dto.response.MenuLateralResponse;
import com.roderly.microbiomelabufu.menu.dto.response.SubMenuResponse;
import com.roderly.microbiomelabufu.menu.dto.response.VueRouterResponse;
import com.roderly.microbiomelabufu.menu.model.Menu;
import com.roderly.microbiomelabufu.menu.model.SubMenu;
import com.roderly.microbiomelabufu.menu.model.VueRouter;

import java.util.List;

public class MenuMapper {

    public static MenuLateralResponse menuLateralToMenuLateralResponse(Menu menu) {

        var vouRoute = menu.getVueRouter() != null ? MenuMapper.vueRouterToVueRouterResponse(menu.getVueRouter()) : null;

        List<SubMenuResponse> subMenus= null;
        if(menu.getSubMenuList() != null) {
            subMenus = menu.getSubMenuList().stream()
                    .map(MenuMapper::SubMenuTosubMenuResponse)
                    .toList();
        }

        return new MenuLateralResponse(
                menu.getIcon(),
                menu.getTitle(),
                menu.getActivator(),
                subMenus,
                vouRoute
        );
    }


    public static VueRouterResponse vueRouterToVueRouterResponse(VueRouter vueRouter) {

        return new VueRouterResponse(
                vueRouter.getPath(),
                vueRouter.getName(),
                vueRouter.getComponent(),
                vueRouter.getTitle()
        );
    }


    public static SubMenuResponse SubMenuTosubMenuResponse(SubMenu subMenu){
        var vouRoute = subMenu.getVueRouter() != null ? MenuMapper.vueRouterToVueRouterResponse(subMenu.getVueRouter()) : null;
        return new SubMenuResponse(
                subMenu.getTitle(),
                vouRoute
        );
    }
}
