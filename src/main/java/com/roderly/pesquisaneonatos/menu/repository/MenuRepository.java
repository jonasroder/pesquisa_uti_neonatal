package com.roderly.pesquisaneonatos.menu.repository;

import com.roderly.pesquisaneonatos.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
