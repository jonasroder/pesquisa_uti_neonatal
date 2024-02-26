CREATE TABLE menu
(
    id_menu       BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_vue_router BIGINT,
    icon          VARCHAR(250),
    title         VARCHAR(250),
    activator     VARCHAR(250) UNIQUE,
    ordem         BIGINT,
    is_active     TINYINT DEFAULT 1
);


