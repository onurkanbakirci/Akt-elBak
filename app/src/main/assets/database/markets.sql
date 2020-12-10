BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "markets" (
	"uID"	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	"marketName"	TEXT,
	"marketLogo"	TEXT
);
INSERT INTO "markets" VALUES (1,'BIM','https://www.bim.com.tr/templates/images/bim-logo-single.png');
INSERT INTO "markets" VALUES (2,'A101','https://ayb.akinoncdn.com/static_omnishop/ayb519/assets/img/logo%40a101-2x.png');
INSERT INTO "markets" VALUES (3,'HAKMAR','//st1.myideasoft.com/idea/el/24/themes/selftpl_5ea0634b2cbf6/assets/uploads/logo.png?revision=1595937209');
INSERT INTO "markets" VALUES (4,'METRO','https://www.metro-tr.com/Sublayouts/Shared/Controls/Components/Headers/www.metro-tr.com/~/media/TR-Metro/metro-logo-tr.png');
COMMIT;
