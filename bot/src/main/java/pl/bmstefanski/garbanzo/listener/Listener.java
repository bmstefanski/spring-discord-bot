package pl.bmstefanski.garbanzo.listener;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public interface Listener<T extends Event> {

  @SubscribeEvent
  void execute(T event);

}
