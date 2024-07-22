
INSERT INTO brands (name, view_rank, created_dt, updated_dt) VALUES
                                                              ('BRAND_A', 1, NOW(), NOW()),
                                                              ('BRAND_B', 2, NOW(), NOW()),
                                                              ('BRAND_C', 3, NOW(), NOW()),
                                                              ('BRAND_D', 4, NOW(), NOW()),
                                                              ('BRAND_E', 5, NOW(), NOW()),
                                                              ('BRAND_F', 6, NOW(), NOW()),
                                                              ('BRAND_G', 7, NOW(), NOW()),
                                                              ('BRAND_H', 8, NOW(), NOW()),
                                                              ('BRAND_I', 9, NOW(), NOW());

INSERT INTO categories (name, created_dt, updated_dt) VALUES
                                                          ('Top', NOW(), NOW()),
                                                          ('Outer', NOW(), NOW()),
                                                          ('Bottom', NOW(), NOW()),
                                                          ('Sneakers', NOW(), NOW()),
                                                          ('Bag', NOW(), NOW()),
                                                          ('Hat', NOW(), NOW()),
                                                          ('Socks', NOW(), NOW()),
                                                          ('Accessories', NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (1, 1, 'A_Top', 11200, 1, NOW(), NOW()),
                                                                                              (1, 2, 'A_Outer', 5500, 1, NOW(), NOW()),
                                                                                              (1, 3, 'A_Bottom', 4200, 1, NOW(), NOW()),
                                                                                              (1, 4, 'A_Sneakers', 9000, 2, NOW(), NOW()),
                                                                                              (1, 5, 'A_Bag', 2000, 1, NOW(), NOW()),
                                                                                              (1, 6, 'A_Hat', 1700, 1, NOW(), NOW()),
                                                                                              (1, 7, 'A_Socks', 1800, 1, NOW(), NOW()),
                                                                                              (1, 7, 'A_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (1, 8, 'A_Accessories', 2300, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (2, 1, 'B_Top', 10500, 1, NOW(), NOW()),
                                                                                              (2, 2, 'B_Outer', 5900, 1, NOW(), NOW()),
                                                                                              (2, 3, 'B_Bottom', 3800, 1, NOW(), NOW()),
                                                                                              (2, 4, 'B_Sneakers', 9100, 1, NOW(), NOW()),
                                                                                              (2, 5, 'B_Bag', 2100, 1, NOW(), NOW()),
                                                                                              (2, 6, 'B_Hat', 2000, 1, NOW(), NOW()),
                                                                                              (2, 7, 'B_Socks', 2000, 2, NOW(), NOW()),
                                                                                              (2, 7, 'B_Socks2', 2000, 1, NOW(), NOW()),
                                                                                              (2, 8, 'B_Accessories', 2200, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (3, 1, 'C_Top', 10000, 1, NOW(), NOW()),
                                                                                              (3, 2, 'C_Outer', 6200, 1, NOW(), NOW()),
                                                                                              (3, 3, 'C_Bottom', 3300, 1, NOW(), NOW()),
                                                                                              (3, 4, 'C_Sneakers', 9200, 1, NOW(), NOW()),
                                                                                              (3, 5, 'C_Bag', 2200, 1, NOW(), NOW()),
                                                                                              (3, 6, 'C_Hat', 1900, 1, NOW(), NOW()),
                                                                                              (3, 7, 'C_Socks', 2200, 1, NOW(), NOW()),
                                                                                              (3, 7, 'C_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (3, 8, 'C_Accessories', 2100, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (4, 1, 'D_Top', 10100, 1, NOW(), NOW()),
                                                                                              (4, 2, 'D_Outer', 5100, 1, NOW(), NOW()),
                                                                                              (4, 3, 'D_Bottom', 3000, 1, NOW(), NOW()),
                                                                                              (4, 4, 'D_Sneakers', 9500, 1, NOW(), NOW()),
                                                                                              (4, 5, 'D_Bag', 2500, 1, NOW(), NOW()),
                                                                                              (4, 6, 'D_Hat', 1500, 1, NOW(), NOW()),
                                                                                              (4, 7, 'D_Socks', 2400, 1, NOW(), NOW()),
                                                                                              (4, 7, 'D_Socks', 2800, 1, NOW(), NOW()),
                                                                                              (4, 8, 'D_Accessories', 2000, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (5, 1, 'E_Top', 10700, 1, NOW(), NOW()),
                                                                                              (5, 2, 'E_Outer', 5000, 1, NOW(), NOW()),
                                                                                              (5, 3, 'E_Bottom', 3800, 1, NOW(), NOW()),
                                                                                              (5, 4, 'E_Sneakers', 9900, 1, NOW(), NOW()),
                                                                                              (5, 5, 'E_Bag', 2300, 1, NOW(), NOW()),
                                                                                              (5, 6, 'E_Hat', 1800, 1, NOW(), NOW()),
                                                                                              (5, 7, 'E_Socks', 2100, 1, NOW(), NOW()),
                                                                                              (5, 7, 'E_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (5, 8, 'E_Accessories', 2100, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (6, 1, 'F_Top', 11200, 1, NOW(), NOW()),
                                                                                              (6, 2, 'F_Outer', 7200, 1, NOW(), NOW()),
                                                                                              (6, 3, 'F_Bottom', 4000, 1, NOW(), NOW()),
                                                                                              (6, 4, 'F_Sneakers', 9300, 1, NOW(), NOW()),
                                                                                              (6, 5, 'F_Bag', 2100, 1, NOW(), NOW()),
                                                                                              (6, 6, 'F_Hat', 1600, 1, NOW(), NOW()),
                                                                                              (6, 7, 'F_Socks', 2300, 1, NOW(), NOW()),
                                                                                              (6, 7, 'F_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (6, 8, 'F_Accessories', 1900, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (7, 1, 'G_Top', 10500, 1, NOW(), NOW()),
                                                                                              (7, 2, 'G_Outer', 5800, 1, NOW(), NOW()),
                                                                                              (7, 3, 'G_Bottom', 3900, 1, NOW(), NOW()),
                                                                                              (7, 4, 'G_Sneakers', 9000, 1, NOW(), NOW()),
                                                                                              (7, 5, 'G_Bag', 2200, 1, NOW(), NOW()),
                                                                                              (7, 6, 'G_Hat', 1700, 1, NOW(), NOW()),
                                                                                              (7, 7, 'G_Socks', 2100, 1, NOW(), NOW()),
                                                                                              (7, 7, 'G_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (7, 8, 'G_Accessories', 2000, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (8, 1, 'H_Top', 10800, 1, NOW(), NOW()),
                                                                                              (8, 2, 'H_Outer', 6300, 1, NOW(), NOW()),
                                                                                              (8, 3, 'H_Bottom', 3100, 1, NOW(), NOW()),
                                                                                              (8, 4, 'H_Sneakers', 9700, 1, NOW(), NOW()),
                                                                                              (8, 5, 'H_Bag', 2100, 1, NOW(), NOW()),
                                                                                              (8, 6, 'H_Hat', 1600, 1, NOW(), NOW()),
                                                                                              (8, 7, 'H_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (8, 7, 'H_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (8, 8, 'H_Accessories', 2000, 1, NOW(), NOW());

INSERT INTO products (brand_id, category_id, name, price, view_rank, created_dt, updated_dt) VALUES
                                                                                              (9, 1, 'I_Top', 11400, 1, NOW(), NOW()),
                                                                                              (9, 2, 'I_Outer', 6700, 1, NOW(), NOW()),
                                                                                              (9, 3, 'I_Bottom', 3200, 1, NOW(), NOW()),
                                                                                              (9, 4, 'I_Sneakers', 9500, 1, NOW(), NOW()),
                                                                                              (9, 5, 'I_Bag', 2400, 1, NOW(), NOW()),
                                                                                              (9, 6, 'I_Hat', 1700, 1, NOW(), NOW()),
                                                                                              (9, 7, 'I_Socks', 1700, 1, NOW(), NOW()),
                                                                                              (9, 7, 'I_Socks', 2000, 1, NOW(), NOW()),
                                                                                              (9, 8, 'I_Accessories', 2400, 1, NOW(), NOW());