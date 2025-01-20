package com.roderly.pesquisaneonatos.menu.mapper;

import com.roderly.pesquisaneonatos.menu.dto.response.MenuLateralResponse;
import com.roderly.pesquisaneonatos.menu.dto.response.SubMenuResponse;
import com.roderly.pesquisaneonatos.menu.dto.response.VueRouterResponse;
import com.roderly.pesquisaneonatos.menu.model.Menu;
import com.roderly.pesquisaneonatos.menu.model.SubMenu;
import com.roderly.pesquisaneonatos.menu.model.VueRouter;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;

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
                menu.getRole().getIdRole(),
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
