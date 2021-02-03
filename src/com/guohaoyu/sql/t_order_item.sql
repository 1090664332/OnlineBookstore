create table t_order_item(
   `id` int primary key auto_increment,
   `name` varchar(200),
   `count` int,
   `price` decimal(11,2),
   `total_price` decimal(11,2),
   `order_id` varchar(64),
   foreign key(`order_id`) references t_order(`order_id`)
);