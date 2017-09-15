<#macro playerContainer player>
<div class="player-container" id="player-${player.id}" data-id="${player.id}">

    <div class="deck">
        <div class="card ${player.card1}" id="card0"></div>
        <div class="card ${player.card2}" id="card1"></div>
    </div>
    <div class="banner">
        <div class="player-info">
            <div class="player-name" id="player-name">
            ${player.name}
            </div>
            <div class="player-name player-subname" id="player-twitchName">
            ${player.twitchName}
            </div>
        </div>
    </div>
    <div class="percentage">
        <div class="percentage-banner">
            <--TODO: replace-->
            92%
        </div>
    </div>
</div>
</#macro>

<#import "PlayerItem.ftl" as m>
<@m.playerContainer player/>