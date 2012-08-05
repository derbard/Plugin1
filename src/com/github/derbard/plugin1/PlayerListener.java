package com.github.derbard.plugin1;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
	BlockFace n = BlockFace.NORTH;
	BlockFace s = BlockFace.SOUTH;
	BlockFace e = BlockFace.EAST;
	BlockFace w = BlockFace.WEST;
	BlockFace nw = BlockFace.NORTH_WEST;
	BlockFace ne = BlockFace.NORTH_EAST;
	BlockFace sw = BlockFace.SOUTH_WEST;
	BlockFace se = BlockFace.SOUTH_EAST;
	Logger logger = Logger.getLogger("Minecraft");

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (Plugin1.nimbus) {
			Player player = event.getPlayer();
			Block block = player.getLocation().getBlock()
					.getRelative(BlockFace.DOWN);
			Block[] innerBlocks = { block, block.getRelative(n),
					block.getRelative(s), block.getRelative(e),
					block.getRelative(w) };

			Block[] outerBlocks = { block.getRelative(n).getRelative(n),
					block.getRelative(s).getRelative(s),
					block.getRelative(e).getRelative(e),
					block.getRelative(w).getRelative(w), block.getRelative(ne),
					block.getRelative(nw), block.getRelative(se),
					block.getRelative(sw) };

			change(innerBlocks, Material.GOLD_BLOCK);
			change(outerBlocks, Material.AIR);
		}
	}

	public void change(Block[] blocks, Material mat) {
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			if (!block.isLiquid()) {
				block.setType(mat);
			}
		}
	}
}
