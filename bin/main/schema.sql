CREATE TABLE "item" (
    "ItemId" INT PRIMARY KEY NOT NULL,
    "Name" VARCHAR
);

CREATE TABLE "cart" (
    "CartId" INT NOT NULL,
    "Title" VARCHAR NOT NULL,
    "ItemId" INT NOT NULL,
    CONSTRAINT "PK_Cart" PRIMARY KEY ("CartId"),
    CONSTRAINT "FK_CartItemId" FOREIGN KEY ("ItemId") REFERENCES "item" ("ItemId") ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE INDEX "IFK_CartItemId" ON "cart" ("ItemId");

INSERT INTO "item" VALUES (1, 'AC/DC');
INSERT INTO "item" VALUES (2, 'Aerosmith');
INSERT INTO "item" VALUES (3, 'Baby Bouncer');
