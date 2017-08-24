package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Player;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerGetHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        int id = Integer.valueOf(request.params(":id"));
        List<Player> players = BeddoMischerMain.getPlayers();

        Map<String, Object> model = new HashMap<>();

        for (Player player : players) {
            if (player.getId() == id) {
                model.put("player", player.toMap());
                return new ModelAndView(model, "PlayerItem.ftl");
            }
        }
        return null;
    }
}
