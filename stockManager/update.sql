INSERT INTO xx_stock_code_table(stock_code,stock_name,stock_table,is_enable) VALUES ('sz002312', '三泰控股', 'xx_stock_data_santai_holding','1');
INSERT INTO xx_stock_code_table(stock_code,stock_name,stock_table,is_enable) VALUES ('sh600130', '波导股份', 'xx_stock_data_bodao_gufen','1');
INSERT INTO xx_stock_code_table(stock_code,stock_name,stock_table,is_enable) VALUES ('sh601766', '中国中车', 'xx_stock_data_china_crrc','1');

DROP TABLE IF EXISTS `xx_stock_data_santai_holding`;
CREATE TABLE `xx_stock_data_santai_holding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `stock_value_date` varchar(10) NOT NULL COMMENT '数据日期',
  `stock_value_time` varchar(8) NOT NULL COMMENT '数据时间',
  `stock_value` decimal(21,6) NOT NULL COMMENT '当前价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5856 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `xx_stock_data_bodao_gufen`;
CREATE TABLE `xx_stock_data_bodao_gufen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `stock_value_date` varchar(10) NOT NULL COMMENT '数据日期',
  `stock_value_time` varchar(8) NOT NULL COMMENT '数据时间',
  `stock_value` decimal(21,6) NOT NULL COMMENT '当前价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5856 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `xx_stock_data_china_crrc`;
CREATE TABLE `xx_stock_data_china_crrc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `stock_value_date` varchar(10) NOT NULL COMMENT '数据日期',
  `stock_value_time` varchar(8) NOT NULL COMMENT '数据时间',
  `stock_value` decimal(21,6) NOT NULL COMMENT '当前价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5856 DEFAULT CHARSET=utf8;
