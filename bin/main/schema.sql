CREATE TABLE "item" (
    "id" INT PRIMARY KEY NOT NULL,
    "Name" VARCHAR
);

CREATE TABLE "cart" (
    "CartId" INT NOT NULL,
    "Name" VARCHAR NOT NULL,
    "id" INT NOT NULL
);

CREATE INDEX "IFK_Cartid" ON "cart" ("id");

INSERT INTO "item" VALUES (1, 'Crib');
INSERT INTO "item" VALUES (2, 'Bottle Set');
INSERT INTO "item" VALUES (3, 'Baby Bouncer');
INSERT INTO "item" VALUES (4, 'Stroller');
INSERT INTO "item" VALUES (5, 'Mobile');
INSERT INTO "item" VALUES (6, 'Baby Camera');
INSERT INTO "item" VALUES (7, 'Car Seat');