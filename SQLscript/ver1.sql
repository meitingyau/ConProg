CREATE DATABASE conprog2;

CREATE TABLE `farms` (
  `id` varchar(200) NOT NULL PRIMARY KEY,
  `name` varchar(200) not null,
  `address` varchar(200) not null,
  `plants` varchar(200) not null,
  `fertilizers` varchar(200) not null,
  `pesticides` varchar(200) not null,
constraint `plants_id_FK`
    foreign key (`plants`)
    references plants (`id`)
    on delete restrict
    on update cascade,
constraint `fertilizers_id_FK`
    foreign key (`fertilizers`)
    references fertilizers (`id`)
    on delete restrict
    on update cascade,
constraint `pesticides_id_FK`
    foreign key (`pesticides`)
    references pesticides (`id`)
    on delete restrict
    on update cascade
) DEFAULT CHARSET=utf8;

/*cant insert at here*/
INSERT INTO `farms` (`id`, `name`, `address`, `plants`, `fertilizers`, `pesticides`) VALUES
  ('1', 'hao', 'The earth is flat', '1,2,3,4,5', '1,2,3,4,5', '1,2,3,4,5'),
  ('2', 'hao', 'One hundred angels', '1,3,5', '1,3,5', '1,3,5'),
  ('3', 'hao', 'rests on a bull\'s horn', '2,4', '2,4', '2,4'),
  ('4', 'hao', 'The earth is like a ball.', '1,2,3,4,5', '1,3,5', '2,4');
 
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL PRIMARY KEY,
  `name` varchar(128) not null,
  `email` varchar(128) not null,
  `password` varchar(128) not null,
  `phoneNumber` varchar(128) not null,
  `farms` varchar(128) not null ,
constraint `farms_id_FK`
    foreign key (`farms`)
    references farms (`id`)
    on delete restrict
    on update cascade
) DEFAULT CHARSET=utf8;

/*cant insert at here*/
INSERT INTO `users` (`id`, `name`, `email`, `password`, `phoneNumber`, `farms`) VALUES
  ('1', 'hao', 'haohao@email.comt', '123456789', '012-3456789', '1,2,3,4');





CREATE TABLE `activities` (
  `id` varchar(50) NOT NULL PRIMARY KEY,
  `date` date not null,
  `action` varchar(128) not null,
  `type` varchar(128) not null,
  `unit` varchar(128) not null,
  `quantity` double(50,3) not null,
  `field` int(50) not null,
  `row` int(50) not null,
  `farmId` varchar(50) not null,
  `userId` varchar(50) not null ,
/*constraint `farms_id_FK`
    foreign key (`farmId`)
    references farms (`id`)
    on delete restrict
    on update cascade,*/
constraint `user_id_FK`
    foreign key (`userId`)
    references users (`id`)
    on delete restrict
    on update cascade
) DEFAULT CHARSET=utf8;

CREATE TABLE `fertilizers` (
  `id` varchar(50) NOT NULL PRIMARY KEY,
  `name` varchar(128) not null,
  `unitType` varchar(30) not null
) DEFAULT CHARSET=utf8;

INSERT INTO `fertilizers` (`id`, `name`, `unitType`) VALUES
  ('1', 'plants 1', 'pack'),
  ('2', 'plants 2', 'pack'),
  ('3', 'plants 3', 'pack'),
  ('4', 'plants 4', 'pack'),
  ('5', 'plants 5', 'pack');

CREATE TABLE `plants` (
  `id` varchar(50) NOT NULL PRIMARY KEY,
  `name` varchar(128) not null,
  `unitType` varchar(30) not null
) DEFAULT CHARSET=utf8;


INSERT INTO `plants` (`id`, `name`, `unitType`) VALUES
  ('1', 'plants 1', 'mass'),
  ('2', 'plants 2', 'mass'),
  ('3', 'plants 3', 'mass'),
  ('4', 'plants 4', 'mass'),
  ('5', 'plants 5', 'mass');

CREATE TABLE `pesticides` (
  `id` varchar(50) NOT NULL PRIMARY KEY,
  `name` varchar(128) not null,
  `unitType` varchar(30) not null
) DEFAULT CHARSET=utf8;

INSERT INTO `pesticides` (`id`, `name`, `unitType`) VALUES
  ('1', 'plants 1', 'mass'),
  ('2', 'plants 2', 'mass'),
  ('3', 'plants 3', 'mass'),
  ('4', 'plants 4', 'mass'),
  ('5', 'plants ', 'mass');



