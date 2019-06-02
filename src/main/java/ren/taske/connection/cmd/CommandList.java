package ren.taske.connection.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.connection.ConnectionPlugin;
import ren.taske.connection.util.BotUtil;

public class CommandList implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("list");
	}
	
	public static final String MSG_HEAD   = "[[List]]";
	public static final String MSG_STATUS = "%s/%s";
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		List<Player> players = new ArrayList<Player>();
		players.addAll(ConnectionPlugin.server().getOnlinePlayers());
		List<String> messages = new ArrayList<String>();
		messages.add(MSG_HEAD);
		messages.add(String.format(MSG_STATUS, players.size(), ConnectionPlugin.server().getMaxPlayers()));
		for(Player player : players) messages.add(player.getName());
		return BotUtil.genRetMsg(sender, messages.toArray(new String[0]));
	}
	
}
