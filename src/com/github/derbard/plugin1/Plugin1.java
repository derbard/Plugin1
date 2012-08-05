package com.github.derbard.plugin1;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin1 extends JavaPlugin {
	public static boolean nimbus = false;
	Logger logger = Logger.getLogger("Minecraft");
	private Plugin1CommandExecutor executor;
	private PlayerListener pl = new PlayerListener();
	private BlockListener bl = new BlockListener();

	public void onEnable() {
		logger.info(this + " Enabled.");
		executor = new Plugin1CommandExecutor(this);
		getCommand("nimbus").setExecutor(executor);
		getCommand("change").setExecutor(executor);
		getCommand("midas").setExecutor(executor);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.bl, this);
		pm.registerEvents(this.pl, this);
	}

	public void onDisable() {
		logger.info(this + " Disabled.");
	}

}
