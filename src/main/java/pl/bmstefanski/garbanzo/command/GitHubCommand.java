package pl.bmstefanski.garbanzo.command;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import org.json.JSONObject;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.CommandInfo;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;

public class GitHubCommand implements CommandExecutor {

  @CommandInfo(value = "github", minArguments = 1, maxArguments = 1, usage = "<username>")
  @Override
  public void execute(CommandSender commandSender, List<String> args) {
    try {
      HttpResponse<JsonNode> httpResponse = Unirest.get("https://api.github.com/users/{username}")
          .header("Accept", "application/json")
          .routeParam("username", args.get(0))
          .asJson();

      if (httpResponse.getStatus() == 404) {
        MessageEmbed messageEmbeds = new EmbedBuilder()
            .setColor(Color.RED)
            .setFooter("Username doesn't match to any account!", null)
            .build();

        commandSender.sendEmbedMessage(messageEmbeds);
        return;
      }

      JSONObject jsonObject = httpResponse.getBody().getObject();

      String login = jsonObject.getString("login");
      String profileUrl = jsonObject.getString("html_url");
      String bio = jsonObject.isNull("bio") ? "" : String.valueOf(jsonObject.get("bio"));
      String location =
          jsonObject.isNull("location") ? "" : String.valueOf(jsonObject.get("location"));
      String registrationDate = jsonObject.getString("created_at");
      String avatarUrl = jsonObject.getString("avatar_url");
      int followers = jsonObject.getInt("followers");
      int following = jsonObject.getInt("following");
      int repositories = jsonObject.getInt("public_repos");
      int gists = jsonObject.getInt("public_gists");

      MessageEmbed messageEmbed = new EmbedBuilder()
          .setColor(Color.BLACK)
          .setThumbnail(avatarUrl)
          .addField("Name", "[" + login + "]" + "(" + profileUrl + ")", true)
          .addField("Bio", bio, true)
          .addField("Location", location, true)
          .addField("Registration date", registrationDate, true)
          .addField("Followers", String.valueOf(followers), true)
          .addField("Following", String.valueOf(following), true)
          .addField("Repositories", String.valueOf(repositories), true)
          .addField("Gists", String.valueOf(gists), true)
          .build();

      commandSender.sendEmbedMessage(messageEmbed);
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }

}
