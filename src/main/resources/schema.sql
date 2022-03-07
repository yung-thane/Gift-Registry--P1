CREATE TABLE "items" (
    "ItemId" INT PRIMARY KEY NOT NULL,
    "Name" VARCHAR, 
    "BuyAvg" INT,
    "SellAvg" INT,
    "Profit" INT
);

/*
CREATE TABLE "album"(
    "AlbumId" INT NOT NULL,
    "Title" VARCHAR NOT NULL,
    "ArtistId" INT NOT NULL,
    /*Making Primary Key for "Album" named "PK_Album", which points at "AlbumId" which was set earlier in the table.
    It can be preferred to not name Primary Key when first setting column variable, so that you can explicitly name it later.
    
    CONSTRAINT "PK_Album" PRIMARY KEY("AlbumId"),
    /*Makes Foreign Key named "FK_AlbumArtistId" which points at the "ArtistId" set earlier in this table. The Foreign Key then is set to reference
    the "ArtistId" column variable which is in the "Artist" TABLE. This table  cannot be deleted or updated.  
    
    CONSTRAINT "FK_AlbumArtistId" FOREIGN KEY ("ArtistId") REFERENCES "artist" ("ArtistId") ON DELETE NO ACTION ON UPDATE NO ACTION
);
*\


CREATE INDEX "IFK_AlbumArtistId" ON "album" ("ArtistId");

/*Inserting elements into the database.

INSERT INTO "artist" VALUES (1, 'AC/DC');
INSERT INTO "artist" VALUES (2, 'Aerosmith');
*/