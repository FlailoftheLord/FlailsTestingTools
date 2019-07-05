package me.flail.ftt;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Commands {
	private CommandSender sender;
	private Command command;

	public Commands(CommandSender sender, Command command) {
		this.command = command;
		this.sender = sender;
	}

	public boolean cmd(String label, String[] args) {
		if (command.getName().equals("plugin")) {

			switch (args.length) {
			case 0:
				sender.sendMessage(FTT.chat("%prefix% &cUsage&8: &7/plugin [<plugin-name>] <enable:disable:reload>"));

				break;
			case 1:
				Plugin plugin = Bukkit.getPluginManager().getPlugin(args[0]);
				if (plugin != null) {
					sender.sendMessage(FTT.chat("%prefix &cUsage&8: &7/plugin " + args[0] + " <enable:disable:reload>"));

					break;
				}

				sender.sendMessage(FTT.chat("%prefix% &4Invalid Plugin! &7" + args[0] + " &cis not a valid plugin!"));
				break;
			case 2:
				plugin = Bukkit.getPluginManager().getPlugin(args[0]);

				if (plugin != null) {
					switch (args[1].toLowerCase()) {
					case "enable":
						PluginTools.enablePlugin(plugin);

						sender.sendMessage(FTT.chat("%prefix% &aEnabled plugin&8: &7" + plugin.getName()));
						break;
					case "disable":
						PluginTools.disablePlugin(plugin);

						sender.sendMessage(FTT.chat("%prefix% &cDisabled plugin&8: &7" + plugin.getName()));
						break;
					case "reload":
						PluginTools.reloadPlugin(plugin);

						sender.sendMessage(FTT.chat("%prefix% &3Reloaded plugin&8: &7" + plugin.getName()));
						break;
					default:
						sender.sendMessage(
								FTT.chat("%prefix% &cUsage&8: &7/plugin " + plugin.getName() + " <enable:disable:reload>"));
						return true;
					}

					break;
				}

				sender.sendMessage(FTT.chat("%prefix% &4Invalid Plugin! &7" + args[0] + " &cis not a valid plugin!"));
				break;
			}

			return true;
		}

		if (command.getName().equals("ftt")) {
			if ((sender instanceof Player)) {
				Player operator = (Player) sender;

				switch (args.length) {
				case 0:
					List<String> hMsg = new ArrayList<>();
					hMsg.add("&2&lFlails Testing Tools &7&lComands");
					hMsg.add("&8&m----=--=-------=---------=--------");
					hMsg.add("  &8- &7/gmc <player>");
					hMsg.add("  &8- &7/gms <player>");
					hMsg.add("  &8- &7/gma <player>");
					hMsg.add("  &8- &7/gmsp <player>");
					hMsg.add("  &8- &7/fly <player>");
					hMsg.add("  &8- &7/op <player>");
					hMsg.add("  &8- &7/deop <player>");
					hMsg.add("  &8- &7/plugin <name> <enable:disable:relaod>");

					for (String line : hMsg) {
						operator.sendMessage(FTT.chat(line));
					}

					break;
				case 1:
					switchArgs(operator, null, args[0]);
					break;
				case 2:
					Player player = Bukkit.getPlayer(args[1]);

					switchArgs(operator, player, args[0]);
					break;
				default:
					player = Bukkit.getPlayer(args[1]);

					switchArgs(operator, player, args[0]);
					break;
				}

				return true;
			}

			sender.sendMessage(FTT.chat("%prefix% &cYOU MUST USE THIS COMAND IN GAME!"));
			return false;
		}

		return true;
	}

	private void switchArgs(Player operator, Player player, String arg) {
		Player target = operator;

		if (player != null) {
			target = player;
		}

		switch (arg) {
		case "gmc":
			target.setGameMode(GameMode.CREATIVE);

			target.sendMessage(FTT.chat("%prefix% &aSet gamemode to Creative"));
			break;
		case "gma":
			target.setGameMode(GameMode.ADVENTURE);

			target.sendMessage(FTT.chat("%prefix% &aSet gamemode to Adventure"));
			break;
		case "gms":
			target.setGameMode(GameMode.SURVIVAL);

			target.sendMessage(FTT.chat("%prefix% &aSet gamemode to Survival"));
			break;
		case "gmsp":
			target.setGameMode(GameMode.SPECTATOR);

			target.sendMessage(FTT.chat("%prefix% &aSet gamemode to Spectator"));
			break;
		case "fly":
			target.setAllowFlight(!(target.getAllowFlight()));
			target.setFlying(target.getAllowFlight());

			target.sendMessage(FTT.chat("%prefix% &aSet flying " + (target.getAllowFlight() ? "&3on" : "&coff")));
			break;
		case "op":
			target.setOp(true);

			target.sendMessage(FTT.chat("%prefix% &cYou've been Op'ed!"));
			break;
		case "deop":
			target.setOp(false);

			target.sendMessage(FTT.chat("%prefix% &cYou've been De-Op'ed!"));
			break;
		case "heal":
			target.setHealth(20);
			target.setFoodLevel(24);

			target.sendMessage(FTT.chat("%prefix% &6You've been healed!"));
			break;
		}
	}

}
