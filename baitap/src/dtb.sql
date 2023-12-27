use users;
CREATE TABLE user (
    id INT primary key auto_increment,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    class_room varchar(255) NOT NULL
)
