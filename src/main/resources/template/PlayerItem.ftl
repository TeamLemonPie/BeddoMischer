<#macro playerContainer player>
<div class="player-container" id="player-${player.id}" data-id="${player.id}" data-hide="${player.state}">
    <div class="top-area">
        <div class="deck-container">
            <div class="deck">
                <div class="card-stack">
                    <div class="card ${player.card1}" id="card0"></div>
                    <div class="card" id="card0_ghost"></div>
                </div>
                <div class="card-stack">
                    <div class="card ${player.card2}" id="card1"></div>
                    <div class="card" id="card1_ghost"></div>
                </div>
            </div>
        </div>
        <div class="percentage-banner">
            <div class="percentage">
            <#--${player.winprobability}%-->
                52%
            </div>
        </div>
    </div>
    <div class="banner">
        <div class="player-info">
            <div class="player-name" id="player-name">
                <#if player.name??>${player.name}</#if>
            </div>
            <div class="player-name player-subname" id="player-twitchName">
                <#if player.twitchName??>${player.twitchName}</#if>
            </div>
        </div>
    </div>
</div>
</#macro>

<#import "PlayerItem.ftl" as m>
<@m.playerContainer player/>