package com.roderly.microbiomelabufu.menu.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
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
@Table(name = "vue_router")
@Entity(name = "VueRouter")
public class VueRouter extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vue_router;
    private String path;
    private String name;
    private String component;
    private String title;

    @OneToMany(mappedBy = "vueRouter")
    private List<SubMenu> subMenuList;

    @OneToMany(mappedBy = "vueRouter")
    private List<Menu> menuList;

}
