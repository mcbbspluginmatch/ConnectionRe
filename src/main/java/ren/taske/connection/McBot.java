package ren.taske.connection;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.sender.IcqHttpApi;
import cc.moecraft.logger.environments.ColorSupportLevel;
import ren.taske.connection.cmd.CommandList;
import ren.taske.connection.cmd.CommandNickname;
import ren.taske.connection.cmd.CommandNicknameAdmin;
import ren.taske.connection.cmd.CommandSilent;
import ren.taske.connection.cmd.CommandWhitelist;

public class McBot implements Runnable {

	final PicqBotX bot;
	
	public McBot(int in, int out, String url) {
		
		PicqConfig config = new PicqConfig(in).setColorSupportLevel(ColorSupportLevel.DISABLED);
		bot = new PicqBotX(config);
		bot.addAccount("BOT", url, out);
		bot.enableCommandManager(Config.CMD_PREFIX);
		bot.getEventManager().registerListener(new TencentMessage());
		
		bot.getCommandManager().registerCommands(new CommandWhitelist(), new CommandNickname(), new CommandSilent(), new CommandNicknameAdmin(), new CommandList());
		
	}
	
	public void run() {
		bot.startBot();
	}
	
	public IcqHttpApi getApi() {
		return bot.getAccountManager().getNonAccountSpecifiedApi();
	}
	
}
