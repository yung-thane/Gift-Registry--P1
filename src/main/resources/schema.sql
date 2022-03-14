CREATE TABLE "item" (
    "ItemId" INT PRIMARY KEY NOT NULL,
    "Name" VARCHAR
);

CREATE TABLE "cart" (
    "CartId" INT NOT NULL,
    "Name" VARCHAR NOT NULL,
    "ItemId" INT NOT NULL,
    CONSTRAINT "PK_Cart" PRIMARY KEY ("CartId"),
    CONSTRAINT "FK_CartItemId" FOREIGN KEY ("ItemId") REFERENCES "item" ("ItemId") ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE INDEX "IFK_CartItemId" ON "cart" ("ItemId");

INSERT INTO "item" VALUES (1, 'Crib');
INSERT INTO "item" VALUES (2, 'Bottle Set');
INSERT INTO "item" VALUES (4, 'Baby Bouncer');
INSERT INTO "item" VALUES (5, 'Stroller');
INSERT INTO "item" VALUES (6, 'Mobile');
INSERT INTO "item" VALUES (7, 'Baby Camera');
INSERT INTO "item" VALUES (8, 'Car Seat');
INSERT INTO "cart" VALUES (1, 'Formula', 1);