CREATE TABLE sub_menu
(
    id_sub_menu   BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_menu       BIGINT,
    id_vue_router BIGINT,
    title         VARCHAR(250),
    is_active     TINYINT DEFAULT 1
);


