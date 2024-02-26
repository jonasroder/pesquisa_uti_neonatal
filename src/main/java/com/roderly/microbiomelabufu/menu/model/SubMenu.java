package com.roderly.microbiomelabufu.menu.model;

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
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sub_menu;
    private String title;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_vue_router", referencedColumnName = "id_vue_router")
    private VueRouter vueRouter;

    @ManyToOne
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    private Menu menu;
}
