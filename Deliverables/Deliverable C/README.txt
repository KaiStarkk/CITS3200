STINT Analyser for Catapult Sprint README
=========================================

INTRODUCTION
=============

STINT Analyser is a side program designed to be used alongside Catapult Sprint, a GPS based sports analysis program. STINT Analyser removes the work of manually selecting playing times of players to be analysed in a game.

STINT Analyser reads in pre-made .csv files created from Catapult Sprint. These files are then processed and outputted as a .vid file for use in Catapult Sprint.

REQUIREMENTS
==============

- Windows XP/7/8
- Java JRE8
- Catapult Sprint

INSTALLATION
============

Install as per instructions in INSTALL.txt.

CONFIGURATION
==============

In order to use STINT Analyser, .csv files must be exported from Catapult Sprint having the correct fields in this order:
Time, Plyr. Load, GPS Time, GPS Latitude, GPS Longidtude

To create a .csv file with the correct fields:
1. Open Catapult Sprint and load a .raw player file in the Graph Setup tab
2. In the Data/Export tab select the 5 columns above and ensure the order is matching
3. Select "GPS Rate"
4. Select "Export to CSV" and save the .csv file in the same folder as the .raw file

Upon opening STINT Analyser, a graphical user interface (GUI) appears.

User then can fill in the various fields including:
1. Select the player .csv files that are to be analysed/the folder where .csv files for the match are stored
2. Select the grounds library file
3. Select the grounds for the match
4. Input start/finish times for each period of the match
5. Press Analyse.

AUTHORS /  CONTRIBUTIONS
========================

Concept:
Mr Ted Polglaze 			ted.polglaze@research.uwa.edu.au

Supervisor:
Prof. Michael Wise 			michael.wise@uwa.edu.au

Authors:
Dean Robert Cook 			21146117@student.uwa.edu.au
Ashwin Daniel D’Cruz			21107248@student.uwa.edu.au
Cameron James Johnson			21149528@student.uwa.edu.au
Marcus Pham				20495924@student.uwa.edu.au
Kieran Matthew Hannigan			21151118@student.uwa.edu.au
Alexander Henry Tonkin			21123293@student.uwa.edu.au
