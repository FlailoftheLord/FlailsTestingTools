package me.flail.ftt;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TabCompleter extends ArrayList<String> {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 8202513963765471021L;

	public TabCompleter() {
	}

	public TabCompleter construct(Command command, String[] args) {
		try {
			String[] arguments = command.getUsage().split(" ");
			for (String line : this.parseArgs(arguments[args.length])) {
				if (line.contains("%")) {
					if ((args.length > 1) && line.split("%")[0].equalsIgnoreCase(args[args.length - 2])) {
						this.add(line.split("%")[1]);
					}

					continue;
				}

				this.add(line);
			}

		} catch (Throwable t) {
		}

		if (command.getName().equalsIgnoreCase("plugin")) {

			if (this.contains("-name")) {
				this.remove("-name");
				for (Plugin pl : Bukkit.getPluginManager().getPlugins()) {
					String plName = pl.getName();

					this.add(plName);
				}
			}

		} else {
			if (this.contains("-player")) {
				this.remove("-player");
				for (Player p : Bukkit.getOnlinePlayers()) {
					this.add(p.getName());
				}

			}

		}

		if ((args[args.length - 1] != " ") && !args[args.length - 1].isEmpty()) {
			for (String comp : this.toArray(new String[] {})) {
				if (!comp.startsWith(args[args.length - 1])) {
					this.remove(comp);
				}

			}

		}


		return this;
	}

	protected String[] parseArgs(String line) {
		String[] chars = { "<", ">", "[", "]" };

		return removeChars(line, chars).split(":");
	}

	protected String removeChars(String message, String[] chars) {
		String modified = message;
		for (String c : chars) {
			modified = modified.replace(c, "");
		}

		return modified;
	}

}
