$( document ).ready(function() 
{
	setTimeout(function(){
		var playerContainer = $("#player-1");
		var playerCard = $(playerContainer).find("#card1");
		var ghostCard = $(playerContainer).find("#card1_ghost");
		
		var newCard = "Ka-8";

		playerCard.fadeOut(1000);
		
		ghostCard.removeAttr("class");
		ghostCard.addClass("card");
		ghostCard.addClass(newCard);
		ghostCard.fadeOut(0);
		
		ghostCard.fadeIn(1000, function() {
			playerCard.removeAttr("class");
			playerCard.addClass("card");
			playerCard.addClass(newCard);
			playerCard.fadeIn(0);
			ghostCard.removeAttr("class");			
		});	
		
	}, 1000);	
});