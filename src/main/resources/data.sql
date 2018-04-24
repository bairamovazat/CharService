INSERT INTO messenger_user(id, email, name, login, hash_password, role, state)
  SELECT 1, 'bairamovazat@gmail.com', 'Администратор', 'admin', '$2a$10$JbeKH3wDIxcgjcdywZ7Douica2D26i35iusZ2svCYL.Ch8lLcX0Je', 'ADMIN','ACTIVATED'
  WHERE
    NOT EXISTS(
        SELECT id
        FROM messenger_user
        WHERE id = 1
    );