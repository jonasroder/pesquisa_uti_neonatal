package com.roderly.pesquisaneonatos.menu.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import com.roderly.pesquisaneonatos.usuario.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
@Entity(name = "Menu")
public class Menu extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_menu;
    private String icon;
    private String title;
    private String activator;
    private Long ordem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "menu")
    private List<SubMenu> subMenuList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vue_router", referencedColumnName = "id_vue_router")
    private VueRouter vueRouter;
}
