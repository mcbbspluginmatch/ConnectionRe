package ren.taske.connection.cmd;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;
import ren.taske.connection.ConnectionPlugin;

public class CommandWhitelist implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("whitelist", "wl");
	}
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		if(args.size() != 2) {
			return getHelp(sender);
		}
		if(args.size() == 2) {
			if(args.get(0).equalsIgnoreCase("add")) {
				String msg;
				if(ConnectionPlugin.isOwner(sender.getId())) {
					String username = args.get(1);
					@SuppressWarnings("deprecation")
					OfflinePlayer player = ConnectionPlugin.server().getOfflinePlayer(username);
					player.setWhitelisted(true);
					msg = getOperateDone(sender, player, true);
				} else {
					msg = getNoPerm(sender);
				}
				return msg;
			}
			if(args.get(0).equalsIgnoreCase("remove")) {
				String msg;
				if(ConnectionPlugin.isOwner(sender.getId())) {
					String username = args.get(1);
					@SuppressWarnings("deprecation")
					OfflinePlayer player = ConnectionPlugin.server().getOfflinePlayer(username);
					player.setWhitelisted(false);
					msg = getOperateDone(sender, player, false);
				} else {
					msg = getNoPerm(sender);
				}
				return msg;
			}
		}
		
		
		return "// Unknwon Command";
	}
	
	public String getHelp(User user) {
		
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(user.getId())).newLine();
		mb.add("[[Whitelist]]").newLine();
		mb.add("!whitelist add <player>");
		mb.add("!whitelist remove <player>");
		
		return mb.toString();
		
	}
	
	public String getOperateDone(User user, OfflinePlayer player, boolean add) {
		
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(user.getId())).newLine();
		mb.add("[[Whitelist]]").newLine();
		mb.add(String.format("Set %s to"+(add?" ":" not ")+"whitelisted", player.getName()));
		
		return mb.toString();
	}
	
	public String getNoPerm(User user) {
		
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(user.getId())).newLine();
		mb.add("[[Whitelist]]").newLine();
		mb.add("You don't have permission to do so!");
		
		return mb.toString();
		
	}
	
}
