package com.ems.minecraft.plotmenu.command;

import com.ems.minecraft.plotmenu.PlotMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {


    private final PlotMenu plotMenu;

    public ReloadConfigCommand(PlotMenu plotMenu) {
        this.plotMenu = plotMenu;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plotMenu.reloadConfig();
        plotMenu.getLogger().info("Config reloaded...");
        return true;
    }
}
