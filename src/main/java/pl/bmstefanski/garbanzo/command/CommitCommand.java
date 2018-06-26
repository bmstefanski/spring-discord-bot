package pl.bmstefanski.garbanzo.command;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.dv8tion.jda.core.EmbedBuilder;
import org.json.JSONArray;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.CommandInfo;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;

public class CommitCommand implements CommandExecutor {

  @CommandInfo(value = "commit", minArguments = 1, maxArguments = 2, usage = "<username> [2017/Yyyy-Mm-Dd/today]")
  @Override
  public void execute(CommandSender commandSender, List<String> args) {
    try {
      HttpResponse<JsonNode> httpResponse = Unirest
          .get("https://github-contributions-api.now.sh/v1/{username}")
          .header("Accept", "application/json")
          .routeParam("username", args.get(0))
          .asJson();

      Map<String, Integer> commitsPerYearMap = new HashMap<>();
      JSONArray yearsArrayJson = httpResponse.getBody().getObject().getJSONArray("years");

      for (int i = 0; i < yearsArrayJson.length(); i++) {
        commitsPerYearMap.put(yearsArrayJson.getJSONObject(i).getString("year"), yearsArrayJson.getJSONObject(i).getInt("total"));
      }

      if (args.size() == 1) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
            .setColor(Color.GREEN)
            .setTitle(args.get(0) + "'s" + " GitHub activity");

        commitsPerYearMap.forEach((year, commits) -> {
          embedBuilder.addField("Year " + year + ":", String.valueOf(commits), true);
        });

        commandSender.sendEmbedMessage(embedBuilder.build());
        return;
      }

    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }

}
