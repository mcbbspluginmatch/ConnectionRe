package ren.taske.connection.cmd;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;
import ren.taske.connection.database.FileManager;
import ren.taske.connection.util.BotUtil;
import ren.taske.connection.util.McUtil;
import ren.taske.data.util.ParseUtil;
import ren.taske.user.TencentUser;

public class CommandNicknameAdmin implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("nickname-admin");
	}
	
	public static final String[] MSG_HELP = new String[] {
		"[[Nickname-Admin/OP]]",
		"!nickname-admin <qqid>",
		"!nickname-admin <qqid> <nickname>"
	};
	
	public static final String MSG_WRONG_USERID = "The userid is probably wrong!";
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		if(!BotUtil.isOwner(sender)) return BotUtil.getUnauthorizedMessage(sender);
		
		int length = args.size();
		if(length > 0) {
			Long userid = ParseUtil.parseLong(args.get(0));
			if(userid == null) {
				return BotUtil.genRetMsg(sender, MSG_WRONG_USERID);
			} else {
				TencentUser user = FileManager.getTencentUser(userid);
				boolean changed = false;
				if(length > 1) {
					user.setNickname(args.get(1));
					changed = true;
					McUtil.bc(String.format(ChatColor.GREEN+"%s(%s)"+ChatColor.RESET+" changed nickname just now", user.getNickname(), user.getUserId()));
				}
				return retNickname(userid, user.getNickname(), sender, changed);
			}
		}
		
		return BotUtil.genRetMsg(sender, MSG_HELP);
	}
	
	public static String retNickname(long userid, String nickname, User sender, boolean isNew) {
		
		MessageBuilder mb = new MessageBuilder();
		
		mb.add(new ComponentAt(sender.getId())).newLine();
		mb.add(userid+"'s nickname"+(isNew?" now ":" ")+"is").newLine();
		mb.add(nickname);
		
		return mb.toString();
		
	}
	
}
