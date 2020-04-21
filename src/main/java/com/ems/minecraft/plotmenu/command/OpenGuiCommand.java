package com.ems.minecraft.plotmenu.command;

import com.ems.minecraft.plotmenu.PlotMenu;
import com.ems.minecraft.plotmenu.gui.PlotMenuGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenGuiCommand  implements CommandExecutor {


    private final PlotMenu plotMenu;
    private final PlotMenuGui plotMenuGui;

    public OpenGuiCommand(PlotMenu plotMenu, PlotMenuGui plotMenuGui) {
        this.plotMenu = plotMenu;
        this.plotMenuGui = plotMenuGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(plotMenuGui.getInventory());
            return true;
        }
        return false;
    }
}
