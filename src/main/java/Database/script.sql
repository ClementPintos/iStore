CREATE TABLE Store (
    id_store INT PRIMARY KEY AUTO_INCREMENT,
    name_store VARCHAR(255)
);
CREATE TABLE User (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255),
    pseudo VARCHAR(255),
    password VARCHAR(255),
    id_store INT,
    role VARCHAR(255),
    whitelisted BOOLEAN,
    FOREIGN KEY (id_store) REFERENCES Store(id_store)
);

CREATE TABLE Inventory (
    id_inventory INT PRIMARY KEY AUTO_INCREMENT,
    id_store INT,
    FOREIGN KEY (id_store) REFERENCES Store(id_store)
);


CREATE TABLE Item (
    id_item INT PRIMARY KEY AUTO_INCREMENT,
    name_item VARCHAR(255),
    price_item DECIMAL(10, 2)
);

CREATE TABLE Inventory_Item (
    id_inventory INT,
    id_item INT,
    quantity_item INT CHECK (quantity_item >= 0),
    PRIMARY KEY (id_inventory, id_item),
    FOREIGN KEY (id_inventory) REFERENCES Inventory(id_inventory),
    FOREIGN KEY (id_item) REFERENCES Item(id_item)
);
