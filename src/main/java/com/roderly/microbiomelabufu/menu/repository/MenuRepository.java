package com.roderly.microbiomelabufu.menu.repository;

import com.roderly.microbiomelabufu.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
