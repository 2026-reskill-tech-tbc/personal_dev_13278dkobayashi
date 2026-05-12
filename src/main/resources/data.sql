-- カテゴリーテーブルデータ
INSERT INTO categories(name) VALUES('本');
INSERT INTO categories(name) VALUES('DVD');
INSERT INTO categories(name) VALUES('ゲーム');
-- 商品テーブルデータ
INSERT INTO items(name, price, image) VALUES('Switch2', 450000, 'switch2-icon.png');
INSERT INTO items(name, price, image) VALUES('ポケットモンスター赤', 10, 'aaa.jpg');

-- 顧客テーブルデータ
INSERT INTO users(name, address, password, email) VALUES('田中太郎', '東京都', 'himitu', 'tanaka@aaa.com');
INSERT INTO users(name, address, password, email) VALUES('鈴木一郎', '大阪府', 'himitu', 'suzuki@aaa.com');
-- 注文テーブルデータ
INSERT INTO orders(user_id, total_price) VALUES(1, 904010);
INSERT INTO orders(user_id, total_price) VALUES(2, 45000);
-- 注文明細テーブルデータ
INSERT INTO order_details(order_id, item_id, quantity) VALUES(1, 1, 2);
INSERT INTO order_details(order_id, item_id, quantity) VALUES(1, 2, 1);
INSERT INTO order_details(order_id, item_id, quantity) VALUES(1, 4, 1);
INSERT INTO order_details(order_id, item_id, quantity) VALUES(2, 1, 1);