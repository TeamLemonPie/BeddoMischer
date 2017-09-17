<#macro chipContainer player index>
<div class="table-line">
    <div class="table-cell table-cell-position <#if player.hide>out</#if>">
        <div class="text-top">${index}.</div>
        <div class="text-bottom">PLATZ</div>
    </div>
    <div class="table-cell-spacer-one"></div>
    <div class="table-cell table-cell-names <#if player.hide>out</#if>">
        <div class="names">
            <div class="text-left">${player.name}</div>
            <div class="text-right">${player.twitchName}</div>
        </div>
    </div>
    <div class="table-cell-spacer-two"></div>
    <div class="table-cell table-cell-chips <#if player.hide>out</#if>">
        <div class="text-top">${player.chips}</div>
        <div class="text-bottom">CHIPS</div>
    </div>
</div>
</#macro>

<#import "ChipItem.ftl" as m>
<@m.chipContainer player "-1"/>