package ren.taske.connection.cmd;

import java.util.ArrayList;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;
import ren.taske.connection.database.FileManager;
import ren.taske.connection.util.BotUtil;
import ren.taske.user.PermissionUser;
import ren.taske.user.TencentUser;

public class CommandSilent implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("silent", "si");
	}
	
	public static final String[] MSG_HELP = new String[] {
		"[[Silent]]", "!silent", "!silent <true/false>"
	};
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		TencentUser tu = FileManager.getTencentUser(sender);
		
		// Permission Check
		// Node: app.silent
		if(!FileManager.getPermission(sender).hasPermission(PermissionUser._APP_SILENT)) return BotUtil.getUnauthorizedMessage(sender);
		
		int length = args.size();
		if(length == 0) {
			return getStatus(sender.getId(), tu.isSilent());
		}
		if(length == 1) {
			boolean value = Boolean.parseBoolean(args.get(0));
			tu.setSilent(value);
			return getStatus(sender.getId(), tu.isSilent());
		}
		return BotUtil.genRetMsg(sender.getId(), MSG_HELP);
	}
	
	public static String getStatus(long id, boolean status) {
		
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(id)).newLine();
		mb.add("You now are "+(status?"silent":"speakable"));
		
		return mb.toString();
		
	}
	
}
