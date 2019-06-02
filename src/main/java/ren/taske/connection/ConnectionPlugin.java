package ren.taske.connection;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import cc.moecraft.icq.sender.IcqHttpApi;

public class ConnectionPlugin extends JavaPlugin {
	
	public static Thread bot_thread;
	public static McBot bot;
	
	private static Server server;
	
	private static Logger logger;
	
	public static boolean botlife;
	
	@Override
	public void onEnable() {
		
		logger = this.getLogger();
		
		server = this.getServer();
		
		getServer().getPluginManager().registerEvents(new McMessage(), this);
		getServer().getPluginManager().registerEvents(new ServerMessage(), this);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(!botlife) {
					bot();
					botlife = true;
					if(Config.SERVER_START_AND_STOP_MESSAGE) {
						getApi().sendGroupMsg(Config.GROUP_ID, "\uff3bSERVER\uff3dStart!");
					}
				}
			}
		}.runTaskLaterAsynchronously(this, 100);
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		
		if(Config.SERVER_START_AND_STOP_MESSAGE) {
			getApi().sendGroupMsg(Config.GROUP_ID, "\uff3bSERVER\uff3dStop!");
		}
		
		super.onDisable();
	}
	
	public static Server server() {
		return server;
	}
	
	void bot() {
		
		int    in  = Config.PORT_IN;
		int    out = Config.PORT_OUT;
		String url = Config.URL_OUT;
		
		log("[Remote Server (CoolQ)] "+url+":"+out);
		log("[Local Server (ConnectionRe)] 127.0.0.1:"+in);
		log("[Command Prefix] "+Config.CMD_PREFIX);
		
		bot = new McBot(in, out, url);
		bot_thread = new Thread(bot);
		bot_thread.start();
		
	}
	
	public static IcqHttpApi getApi() {
		return bot.getApi();
	}
	
	public static void log(String msg) {
		logger.info(msg);
	}
	
	public static boolean isOwner(long id) {
		return Config.OWNER_ID == id;
	}
	
}
