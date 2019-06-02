package ren.taske.connection.cmd;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.connection.ConnectionPlugin;
import ren.taske.connection.util.BotUtil;

public class CommandWhitelist implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("whitelist", "wl");
	}
	
	public static final String[] MSG_HELP = new String[] {
		"[[Whitelist/OP]]", "!whitelist add <player>", "!whitelist remove <player>"	
	};
	
	public static final String MSG_UNAUTHORIZED  = "You have no permission!";
	public static final String MSG_RESULT_ADD    = "Added %s to Whitelist";
	public static final String MSG_RESULT_REMOVE = "Removed %s in Whitelist";
	
	@SuppressWarnings("deprecation")
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		if(!ConnectionPlugin.isOwner(sender.getId())) return BotUtil.genRetMsg(sender.getId(), MSG_UNAUTHORIZED); // Unauthorized
		
		if(args.size() != 2) {
			return BotUtil.genRetMsg(sender.getId(), MSG_HELP);
		} else {
			// -1: wrong arguments
			//  0: remove
			//  1: add
			int type = -1;
			if(args.get(0).equalsIgnoreCase("add"))    type = 1;
			if(args.get(0).equalsIgnoreCase("remove")) type = 0;
			
			if(type == -1) { // Illegal Argument
				return BotUtil.genRetMsg(sender.getId(), MSG_HELP);
			} else {
				boolean whitelisted = type == 1;
				String username = args.get(1);
				OfflinePlayer player = ConnectionPlugin.server().getOfflinePlayer(username);
				player.setWhitelisted(whitelisted);
				return BotUtil.genRetMsg(sender.getId(), String.format(whitelisted?MSG_RESULT_ADD:MSG_RESULT_REMOVE, username));
			}
		}
		
	}
	
}
