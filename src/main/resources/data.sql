INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_MODERATOR');

INSERT INTO users (id, username, password, email) VALUES (1, 'admin', '$2a$10$UqPKe8.IL1HDKAkX1KSJhusYchBY5lpyAbD012GvJKQjMJsxkMtLK', 'admin@admin.admin');

INSERT INTO user_roles (user_id, role_id) VALUES (1,1);
INSERT INTO user_roles (user_id, role_id) VALUES (1,2);
INSERT INTO user_roles (user_id, role_id) VALUES (1,3);