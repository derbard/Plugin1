package com.github.derbard.plugin1;

import java.util.logging.Logger;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Plugin1CommandExecutor implements CommandExecutor {

	private Plugin1 plugin;
	Logger logger;

	public Plugin1CommandExecutor(Plugin1 plugin) {
		this.plugin = plugin;
		logger = this.plugin.logger;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		if (cmd.getName().equalsIgnoreCase("nimbus")) {
			if (player == null) {
				sender.sendMessage("Really, only players should use this command.");
				return false;
			}
			if (Plugin1.nimbus) {
				Plugin1.nimbus = false;
				sender.sendMessage("You no longer have the power of the nimbus cloud!");
				logger.info(sender.getName()
						+ " no longer has the power of the nimbus cloud!");
			} else {
				Plugin1.nimbus = true;
				sender.sendMessage("You now have the power of the nimbus cloud!");
				logger.info(sender.getName()
						+ " has the power of the nimbus cloud!");
			}

			return true;
		} else if (cmd.getName().equalsIgnoreCase("change")) {
			return change(sender, args[0], player);
		} else if (cmd.getName().equalsIgnoreCase("midas")) {
			return change(sender, "gold_block", player);
		}
		return false;
	}

	private boolean change(CommandSender sender, String arg, Player player) {
		sender.sendMessage("Changing blocks.");
		Chunk chunk = player.getLocation().getBlock().getChunk();
		Material mat = Material.getMaterial(arg.toUpperCase());
		if (mat == null) {
			sender.sendMessage("Couldn't find " + arg
					+ " changing to dirt instead.");
			mat = Material.DIRT;
		}
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 128; y++) {
				for (int z = 0; z < 16; z++) {
					Block block = chunk.getBlock(x, y, z);
					if (!block.isEmpty() && !block.isLiquid()
							&& !block.getType().equals(Material.SNOW)
							&& !block.getType().equals(Material.ICE)) {
						block.setType(mat);

					}
				}
			}
		}
		return true;
	}
}
