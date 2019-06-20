package me.flail.ftt;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PluginTools {

	public static void reloadPlugin(Plugin plugin) {
		disablePlugin(plugin);
		enablePlugin(plugin);
	}

	public static void enablePlugin(Plugin plugin) {
		try {
			plugin = Bukkit.getPluginManager().loadPlugin(new File(plugin.getName() + ".jar"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (plugin != null) {
			Bukkit.getPluginManager().enablePlugin(plugin);
		}
	}

	public static void disablePlugin(Plugin plugin) {
		Bukkit.getPluginManager().disablePlugin(plugin);
	}

}
