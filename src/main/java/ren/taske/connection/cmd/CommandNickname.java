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

public class CommandNickname implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("nickname", "nick");
	}
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		TencentUser tu = FileManager.getTencentUser(sender);
		
		// Permission Check
		// Node: app.nickname
		if(!FileManager.getPermission(sender).hasPermission(PermissionUser._APP_NICKNAME)) return BotUtil.getUnauthorizedMessage(sender);
		
		if(args.size() == 0) {
			return retNickname(tu.getNickname(sender), sender, false);
		}
		if(args.size() > 0) {
			tu.setNickname(args.get(0));
			return retNickname(tu.getNickname(sender), sender, true);
		}
		return null;
	}
	
	public static String retNickname(String nickname, User sender, boolean isNew) {
		
		MessageBuilder mb = new MessageBuilder();
		
		mb.add(new ComponentAt(sender.getId())).newLine();
		mb.add("Your nickname"+(isNew?" now ":" ")+"is").newLine();
		mb.add(nickname);
		
		return mb.toString();
		
	}
	
}
