DROP TABLE IF EXISTS alarm;
DROP TABLE IF EXISTS stock_user;
DROP TABLE IF EXISTS stock;

CREATE TABLE public.stock_user (
    	user_id int8 NOT NULL,
    	user_uuid varchar(255) NOT NULL,
    	firstname varchar(255) NULL,
    	lastname varchar(255) NULL,
    	email varchar(255) NULL,
    	password varchar(255) NULL,
    	CONSTRAINT stock_user_pkey PRIMARY KEY (user_id)
);

CREATE TABLE public.stock (
    	stock_id int8 NOT NULL,
    	stock_uuid varchar(255) NOT NULL,
    	stock_name varchar(255) NOT NULL,
    	symbol varchar(255) NULL,
    	current_price real NULL,
    	CONSTRAINT stock_pkey PRIMARY KEY (stock_id)
);

CREATE TABLE public.alarm (
    	alarm_id int8 NOT NULL,
    	user_id int8,
    	stock_id int8,
    	alarm_uuid varchar(255) NOT NULL,
    	alarm_name varchar(255) NULL,
    	saved_price real NULL,
    	target_percentage real NULL,
    	actual_percentage real NULL,
    	status bool NULL,
        CONSTRAINT fk_stock_user FOREIGN KEY (user_id) REFERENCES stock_user(user_id),
        CONSTRAINT fk_stock FOREIGN KEY (stock_id) REFERENCES stock(stock_id),
    	CONSTRAINT alarm_pkey PRIMARY KEY (alarm_id)

);

CREATE SEQUENCE seq_stock_user
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    START WITH 1
    OWNED BY stock_user.user_id;

CREATE SEQUENCE seq_stock
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    START WITH 1
    OWNED BY stock.stock_id;