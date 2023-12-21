create database ak;
use ak;

create table sign (user_Name varchar(100), user_Email varchar(100) , user_Password varchar(50),user_Location varchar(100));
desc sign;
create table flight_Details(
							flight_Name varchar(100) , 
							flight_Id integer, 
                            Destination_city varchar(100), 
                            Departure_city varchar(100),
                            flight_date Date, 
                            flight_Amount integer ,
                            flight_Capacity integer,
                            primary key (flight_Id,flight_date));
desc flight_Details;
drop table flight_Details;

INSERT INTO flight_Details (flight_Name, flight_Id, Destination_city, Departure_city, flight_date, flight_Amount, flight_Capacity)
VALUES
('A', 201, 'NewYork', 'LosAngeles', '2024-01-10', 3500, 60),
('B', 202, 'London', 'Paris', '2024-02-15', 2800, 70),
('C', 203, 'Tokyo', 'Sydney', '2024-03-20', 5800, 50),
('D', 204, 'Dubai', 'NewDelhi', '2024-04-25', 4200, 60),
('E', 205, 'Singapore', 'HongKong', '2024-05-30', 3200, 80),
('F', 206, 'Berlin', 'Rome', '2024-06-05', 4100, 60),
('G', 207, 'SanFrancisco', 'Chicago', '2024-07-10', 3800, 160),
('H', 208, 'Mumbai', 'CapeTown', '2024-08-15', 6700, 120),
('I', 209, 'Barcelona', 'Madrid', '2024-09-20', 2400, 60),
('J', 210, 'Sydney', 'Melbourne', '2024-10-25', 1500, 65),
('K', 211, 'Paris', 'Amsterdam', '2024-11-30', 3000, 97),
('L', 212, 'Seoul', 'Beijing', '2024-12-05', 4200, 99),
('A', 201, 'NewYork', 'LosAngeles', '2024-01-11', 3500, 60),
('B', 202, 'London', 'Paris', '2024-02-16', 2800, 70),
('C', 203, 'Tokyo', 'Sydney', '2024-03-21', 5800, 50),
('D', 204, 'Dubai', 'NewDelhi', '2024-04-26', 4200, 60),
('E', 205, 'Singapore', 'HongKong', '2024-05-31', 3200, 80),
('F', 206, 'Berlin', 'Rome', '2024-06-06', 4100, 60),
('G', 207, 'SanFrancisco', 'Chicago', '2024-07-11', 3800, 160),
('H', 208, 'Mumbai', 'CapeTown', '2024-08-16', 6700, 120),
('I', 209, 'Barcelona', 'Madrid', '2024-09-21', 2400, 60),
('J', 210, 'Sydney', 'Melbourne', '2024-10-26', 1500, 65),
('K', 211, 'Paris', 'Amsterdam', '2024-12-01', 3000, 97),
('L', 212, 'Seoul', 'Beijing', '2024-12-06', 4200, 99);

create table book (	user_Name varchar(100) , 
					flight_Name varchar(100) , 
                    destination_City varchar(100), 
                    departure_City varchar(100),
                    travel_Date Date);
select*from book;
drop table book;
