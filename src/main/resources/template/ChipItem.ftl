<#macro chipContainer player>
<div class="player-container" id="player-${player.id}">
    <div class="banner">
        <div class="player-name" id="player-name">${player.name}</div>
        <div class="player-name player-subname" id="player-twitchName">${player.twitchName}</div>
        <div class="player-name player-subname player-chip" id="player-chip">${player.chips}</div>
    </div>
</div>
</#macro>

<#import "ChipItem.ftl" as m>
<@m.chipContainer player/>