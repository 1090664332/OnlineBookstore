create table t_order(
   `order_id` varchar(64) primary key,
   `craete_time` datetime,
   `price` decimal(11,2),
   `status` int,
   `user_id` int,
   foreign key(`user_id`) references t_user(`id`)
);
