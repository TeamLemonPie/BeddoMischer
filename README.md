## BeddoMischer

BeddoMischer is a tool in the "Beddo" product family. The products are designed to create an overlay for a poker live stream.
It's main purpose is to act as the central unit between multiple instances of BeddoFabrik and a BeddoControl admin interface.
BeddoMischer provides several websites showing different overlays that can be used in live web streams to provide information for the audience.

![](/build/resources/icon_112x112.png) (Icon made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a>)

### Main Features
#### overlays for the audience
show player names, twitch names, cards on hand (as scanned by the NFC scanners) and calculated win probability
![](/build/resources/Overlay_Player.png)
  
show board cards (the five community cards in the center of the table as scanned by the NFC scanners) together with current big blind, small blind and ante
![](/build/resources/Overlay_Board.png)
  
show a ordered list of all players sorted by the amount of remaining chips for each player
![](/build/resources/Overlay_Chips.png)
  
show a countdown timer (countdown until the current break will end)
![](/build/resources/Overlay_Countdown.PNG)
  
#### overlays for the players
Show a so called "PlayerFeedbackPage" that represents the seven seats at the poker table. The purpose for this overview is, that the players at the table can see the success of scanning. Every seat will show the corresponding player name and the scanner status using a four-color-code bar:
  - black: player folded
  - red: no card is scanned
  - orange/yellow: one card is successfully scanned
  - green: both cards are successfully scanned
This allows the players to get feedback about the scanning process so they can re-scan their cards if one or more card is not detected.
![](/build/resources/Overlay_PlayerFeedback.PNG)
  
### Main Communication between components

The main system architecture is based on a client-server infrastructure. The BeddoMischer acts a the server by providing different ports. 
BeddoMischer prodives the following network connections:

* HTTP Rest API (for Overlay)
* WebSocket (for dynamic Overlay)
* TCP Socket (for BeddoControl/BeddoFabrik)
* UCP Broadcast (for discovery)

#### Handling Communication btween BeddoMischer and BeddoFabrik:

manage incoming events of scanned cards from all connected BeddoFabrik systems
- update internal model on server side
- update win probability
- update website overlays via websocket and javascript (This avoids reloading the page which otherwise would result in ugly flashing overlays.)
- send updated data to BeddoControl to allow a UI refresh in BeddoControl (if connected)

propagate clear commands to all connected BeddoFabrik systems in order to clear their internal buffers and prepare for a new round.

#### Handling Communication btween BeddoMischer and BeddoControl:
  - new round
  - master lock/unlock
  - lock/unlock board
  - clear board
  - add/edit/delete players
  - manually set board cards
  - map NFC scanners to seats
  - set big blind/small blind/ante
  - etc. --> for more detailed information see the BeddoControl README
- all internal data will be persisted in a SQLite database (Restarting BeddoMischer will preserve settings and data)

#### Third Party Libraries

* com.google.code.gson:gson:2.8.0 (Apache 2.0)
* com.sparkjava:spark-core:2.7.2 (The Apache Software License, Version 2.0)
* com.sparkjava:spark-template-freemarker:2.7.1 (The Apache Software License, Version 2.0)
* org.xerial:sqlite-jdbc:3.23.1 (The Apache Software License, Version 2.0)
* junit:junit:4.12 (Eclipse Public License 1.0)
* org.assertj:assertj-core:3.10.0 (Apache License, Version 2.0)
* de.lemonpie:BeddoCommon:1.1.0
* de.tobias:libLogger-slf4j:1.0.3
* de.deadlocker8:tools:1.1.0
* de.tobias:libUtils:1.6.7
* de.tobias:libNetwork:1.0.1
* de.tobias:libStorage:1.3.0
