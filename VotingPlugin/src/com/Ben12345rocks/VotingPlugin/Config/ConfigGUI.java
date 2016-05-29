package com.Ben12345rocks.VotingPlugin.Config;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.Ben12345rocks.VotingPlugin.Main;
import com.Ben12345rocks.VotingPlugin.Files.Files;

public class ConfigGUI {

	static ConfigGUI instance = new ConfigGUI();

	static Main plugin = Main.plugin;

	public static ConfigGUI getInstance() {
		return instance;
	}

	FileConfiguration data;

	File dFile;

	private ConfigGUI() {
	}

	public ConfigGUI(Main plugin) {
		ConfigGUI.plugin = plugin;
	}

	public FileConfiguration getData() {
		return data;
	}

	public int getVoteGUISlotAmount(String slot) {
		return getData().getInt("GUI.VoteGUI." + slot + ".Item.Amount");
	}

	public String getVoteGUISlotCommand(String slot) {
		return getData().getString("GUI.VoteGUI." + slot + ".Command");
	}

	public int getVoteGUISlotData(String slot) {
		return getData().getInt("GUI.VoteGUI." + slot + ".Item.Data");
	}

	public int getVoteGUISlotID(String slot) {
		return getData().getInt("GUI.VoteGUI." + slot + ".Item.ID");
	}

	public String getVoteGUISlotName(String slot) {
		return getData().getString("GUI.VoteGUI." + slot + ".Item.Name");
	}

	public Set<String> getVoteGUISlots() {
		return getData().getConfigurationSection("GUI.VoteGUI").getKeys(false);
	}

	public int getVoteURLAlreadyVotedItemAmount() {
		int num = getData().getInt("GUI.VoteURL.AlreadyVotedItem.Amount");
		if (num != 0) {
			return num;
		} else {
			return 1;
		}
	}

	public int getVoteURLAlreadyVotedItemData() {
		int num = getData().getInt("GUI.VoteURL.AlreadyVotedItem.Data");
		if (num != 0) {
			return num;
		} else {
			return 0;
		}
	}

	public int getVoteURLAlreadyVotedItemID() {
		int num = getData().getInt("GUI.VoteURL.AlreadyVotedItem.ID");
		if (num != 0) {
			return num;
		} else {
			return 152;
		}
	}

	public int getVoteURLCanVoteItemAmount() {
		int num = getData().getInt("GUI.VoteURL.CanVoteItem.Amount");
		if (num != 0) {
			return num;
		} else {
			return 1;
		}
	}

	public int getVoteURLCanVoteItemData() {
		int num = getData().getInt("GUI.VoteURL.CanVoteItem.Data");
		if (num != 0) {
			return num;
		} else {
			return 0;
		}
	}

	public int getVoteURLCanVoteItemID() {
		int num = getData().getInt("GUI.VoteURL.CanVoteItem.ID");
		if (num != 0) {
			return num;
		} else {
			return 133;
		}
	}

	public String getVoteURLNextVote() {
		String str = getData().getString("GUI.VoteURL.NextVote");
		if (str != null) {
			return str;
		} else {
			return "&cCan Vote In: %Info%";
		}
	}

	public String getVoteURLSeeURL() {
		String str = getData().getString("GUI.VoteURL.SeeURL");
		if (str != null) {
			return str;
		} else {
			return "&cClick to see URL";
		}
	}

	public String getVoteURLSiteName() {
		String str = getData().getString("GUI.VoteURL.SiteName");
		if (str != null) {
			return str;
		} else {
			return "&c%Name%";
		}
	}

	public void reloadData() {
		data = YamlConfiguration.loadConfiguration(dFile);
	}

	public void saveData() {
		Files.getInstance().editFile(dFile, data);
	}

	public void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		dFile = new File(p.getDataFolder(), "GUI.yml");

		if (!dFile.exists()) {
			try {
				dFile.createNewFile();
				plugin.saveResource("GUI.yml", true);
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create GUI.yml!");
			}
		}

		data = YamlConfiguration.loadConfiguration(dFile);
	}

}
