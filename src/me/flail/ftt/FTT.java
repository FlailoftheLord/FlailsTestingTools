package me.flail.ftt;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FTT extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		for (String cmd : getDescription().getCommands().keySet()) {
			this.getCommand(cmd).setExecutor(this);
			this.getCommand(cmd).setTabCompleter(this);

			getServer().getPluginManager().registerEvents(this, this);
		}

	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return new Commands(sender, command).cmd(label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return new TabCompleter().construct(command, args);
	}

	@EventHandler
	public void onCmdProcess(PlayerCommandPreprocessEvent event) {
		String message = event.getMessage();

		String[] args = { "gmc", "gma", "gms", "gmsp", "fly", "op", "deop" };
		for (String s : args) {
			if (message.startsWith("/" + s)) {
				message = message.replace("/", "/ftt ");
				break;
			}
		}

		event.setMessage(message);
	}

	public static String chat(String message) {
		return ChatColor.translateAlternateColorCodes('&', message.replace("%prefix%", "&8(&2&lFTT&8)"));
	}

}
