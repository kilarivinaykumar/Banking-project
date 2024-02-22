create table account1( ah_id serial,ah_name varchar(20),email varchar(20),balance numeric(16,2),
					 doj date,dob date,created_date date,modified_date timestamp without time zone,primary key(ah_id));
					 
				
create table transation1(tr_id serial,tr_type varchar(20),tr_date date,amount numeric(16,2),
						ah_id serial,created_date date,primary key(tr_id),foreign key(ah_id)
						 references account1(ah_id));
						 
						 create table customer(id serial,cu_name varchar(20),password varchar(20),
 					 email varchar(20),doj date,dob date,created_date date,primary key(id));
					 
					 insert into account1 (ah_name,email,balance,
										  doj,dob,created_date,modified_date)values ('Vinay','vinay@gmail.com',200.00,'2024-03-22','2024-03-22','2024-03-22',now());
					 
					 select * from account1;
					 select * from transation1;
					 select * from customer;
					 
					 
					 
 CREATE OR REPLACE FUNCTION account_banking()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.tr_type = 'Deposite' THEN
        UPDATE account1
        SET balance = balance + NEW.amount, modified_date = CURRENT_TIMESTAMP
        WHERE ah_id = NEW.ah_id;
    ELSIF NEW.tr_type = 'Withdrawal' THEN
        IF (SELECT balance FROM account1 WHERE ah_id = NEW.ah_id) >= NEW.amount THEN
            UPDATE account1
            SET balance = balance - NEW.amount, modified_date = CURRENT_TIMESTAMP
            WHERE ah_id = NEW.ah_id;
        ELSE
            RAISE EXCEPTION 'Insufficient funds';
        END IF;
    ELSE
        RAISE EXCEPTION 'Please choose proper transaction';
    END IF;
    
    RETURN NEW;
END;
$$;




Create trigger transation_bank
after insert 
on transation1
for each row
Execute function account_banking();

insert into transation1(tr_type,tr_date,amount,ah_id,created_date) values(
      'Deposite','2024-03-22',2000,1,'2024-03-22');
	  insert into customer(cu_name,password,email,doj,dob,created_date) values('Vinay','vinay','vinay@gmail.com','2024-03-22','2024-03-22','2024-03-22');
	  


					 

