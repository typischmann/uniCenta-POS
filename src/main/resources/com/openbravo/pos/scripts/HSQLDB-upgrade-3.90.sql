--    uniCenta oPOS - Touch Friendly Point Of Sale
--    Copyright (C) 2009-2015 uniCenta
--    http://www.unicenta.net
--
--    This file is part of uniCenta oPOS.
--
--    uniCenta oPOS is free software: you can redistribute it and/or modify
--    it under the terms of the GNU General Public License as published by
--    the Free Software Foundation, either version 3 of the License, or
--    (at your option) any later version.
--
--    uniCenta oPOS is distributed in the hope that it will be useful,
--    but WITHOUT ANY WARRANTY; without even the implied warranty of
--    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--    GNU General Public License for more details.
--
--    You should have received a copy of the GNU General Public License
--    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

-- Database upgrade script for HSQLDB
-- v3.90 - v3.91

-- Insert new posapps script
DELETE FROM RESOURCES WHERE NAME = 'script.posapps';
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('61', 'script.posapps', 0, $FILE{/com/openbravo/pos/templates/script.posapps.txt});

-- Insert the new posapps image
DELETE FROM RESOURCES WHERE NAME = 'img.posapps';
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('62', 'img.posapps', 1, $FILE{/com/openbravo/pos/templates/img.posapps.png});


-- Update the ticket.buttons resource
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Ticket.Buttons.xml} WHERE NAME='Ticket.Buttons';

-- Update Roles
UPDATE ROLES SET PERMISSIONS = $FILE{/com/openbravo/pos/templates/Role.Administrator.xml} WHERE NAME='Administrator role'; 
UPDATE ROLES SET PERMISSIONS = $FILE{/com/openbravo/pos/templates/Role.Manager.xml} WHERE NAME='Manager role'; 

-- Update Products
UPDATE PRODUCTS SET STOCKCOST = 0.00 WHERE STOCKCOST IS NULL;
UPDATE PRODUCTS SET STOCKVOLUME = 0.00 WHERE STOCKVOLUME IS NULL;

-- UPDATE App' version
UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};

-- Remove SharedTickets
DELETE FROM SHAREDTICKETS;