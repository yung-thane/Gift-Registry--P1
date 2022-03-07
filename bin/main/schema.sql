CREATE TABLE "items" (
    "ItemId" INT PRIMARY KEY NOT NULL,
    "Name" VARCHAR, 
    "BuyAvg" INT,
    "SellAvg" INT,
    "Profit" INT
);



/*Inserting elements into the database.
*/
INSERT INTO "items" VALUES (1, 'Dragon Harpoon', 200, 150, 50);
INSERT INTO "items" VALUES (2, 'Pot', 100, 50, 50);
