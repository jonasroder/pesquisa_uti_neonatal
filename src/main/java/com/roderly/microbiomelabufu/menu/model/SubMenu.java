package com.roderly.microbiomelabufu.menu.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sub_menu")
@Entity(name = "SubMenu")
public class SubMenu extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sub_menu;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vue_router", referencedColumnName = "id_vue_router")
    private VueRouter vueRouter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    private Menu menu;
}
