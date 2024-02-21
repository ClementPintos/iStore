CREATE TABLE Store (
                       id_store INT PRIMARY KEY,
                       name_store VARCHAR(255)
);
CREATE TABLE User (
                      id_user INT PRIMARY KEY,
                      email VARCHAR(255),
                      pseudo VARCHAR(255),
                      password VARCHAR(255),
                      role VARCHAR(255),
                      whitelisted BOOLEAN,
                      id_store INT,
                      FOREIGN KEY (id_store) REFERENCES Store(id_store)
);

CREATE TABLE Item (
                      id_item INT PRIMARY KEY,
                      name_item VARCHAR(255),
                      price_item DECIMAL(10, 2)
);

CREATE TABLE Inventory (
                           id_store INT,
                           id_item INT,
                           quantity_item INT CHECK (quantity_item >= 0),
                           PRIMARY KEY (id_store, id_item),
                           FOREIGN KEY (id_store) REFERENCES Store(id_store),
                           FOREIGN KEY (id_item) REFERENCES Item(id_item)
);

INSERT INTO Item VALUES(1,'Air', '0');
INSERT INTO Store VALUES(1,'Chomage');