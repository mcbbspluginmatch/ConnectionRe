package ren.taske.connection.cmd.mc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ren.taske.user.MUser;

public class CommandSilentMc implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			MUser user = new MUser(sender.getName());
			if(args.length > 0) {
				boolean silent = Boolean.parseBoolean(args[0]);
				user.setSilent(silent);
			}
			sender.sendMessage("You are now "+getMessage(user.isSilent()));
			return true;
		} else {
			sender.sendMessage("You should be a player!");
		}
		return false;
	}
	
	public static String getMessage(boolean isSilent) {
		return isSilent ? "slient" : "speakable";
	}
	
}
