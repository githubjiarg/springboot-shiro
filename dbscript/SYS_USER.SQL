/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.17 
*********************************************************************
*/

create table `sys_user` (
	`id` varchar (192),
	`user_name` varchar (135),
	`password` varchar (300),
	`salt` varchar (300),
	`remark` varchar (135),
	`create_time` date
);

insert into `sys_user` (`id`, `user_name`, `password`, `salt`, `remark`, `create_time` ) values( '1','admin','39a4b7fe0420aa2c7d5f46cc39acf11b','管理员账户','ABC','2019-11-20');
