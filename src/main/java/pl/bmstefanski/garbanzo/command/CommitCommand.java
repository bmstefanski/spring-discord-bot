package pl.bmstefanski.garbanzo.command;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.CommandInfo;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;

public class CommitCommand implements CommandExecutor {

  @CommandInfo(value = "commit", minArguments = 1, maxArguments = 2, usage = "<username> [Yyyy-Mm-Dd]")
  @Override
  public void execute(CommandSender commandSender, List<String> args) {
    try {
      HttpResponse<JsonNode> httpResponse = Unirest
          .get("https://github-contributions-api.now.sh/v1/{username}")
          .header("Accept", "application/json")
          .routeParam("username", args.get(0))
          .asJson();

      JSONArray yearsJsonArray = httpResponse.getBody().getObject().getJSONArray("years");

      if (yearsJsonArray.length() == 0) {
        MessageEmbed messageEmbeds = new EmbedBuilder()
            .setColor(Color.RED)
            .setFooter("Username doesn't match to any account!", null)
            .build();

        commandSender.sendEmbedMessage(messageEmbeds);
        return;
      }

      if (args.size() == 1) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
            .setColor(Color.GREEN)
            .setTitle(args.get(0) + "'s" + " activity");

        for (int i = 0; i < yearsJsonArray.length(); i++) {
          embedBuilder.addField("Year "
              + yearsJsonArray.getJSONObject(i).getString("year")
              + ":", String.valueOf(yearsJsonArray.getJSONObject(i).getInt("total")), true);
        }

        commandSender.sendEmbedMessage(embedBuilder.build());
        return;
      }

      JSONArray contributionsJsonArray = httpResponse.getBody().getObject()
          .getJSONArray("contributions");

      EmbedBuilder embedBuilder = new EmbedBuilder()
          .setColor(Color.RED)
          .setFooter("Cannot find activity from: " + args.get(1), null);

      for (int i = 0; i < contributionsJsonArray.length(); i++) {
        JSONObject jsonObject = contributionsJsonArray.getJSONObject(i);

        if (args.get(1).equals(jsonObject.getString("date"))) {
          embedBuilder.setTitle(args.get(0) + "'s" + " activity");
          embedBuilder.setColor(Color.decode(jsonObject.getString("color")));
          embedBuilder.addField("Searching date", args.get(1), true);
          embedBuilder.addField("Contributions count", String.valueOf(jsonObject.getInt("count")), true);
          embedBuilder.setFooter(null, null);
        }

      }

      commandSender.sendEmbedMessage(embedBuilder.build());
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }

}
