package com.ems.minecraft.plotmenu;

import com.ems.minecraft.plotmenu.command.OpenGuiCommand;
import com.ems.minecraft.plotmenu.command.ReloadConfigCommand;
import com.ems.minecraft.plotmenu.config.ConfigHandler;
import com.ems.minecraft.plotmenu.gui.PlotMenuGui;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.permission.Permissions;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LoadOrder;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

import java.io.File;

@Plugin(name = "PlotMenu", version = "1.0")
@Description(value = "A Plugin for showing a menu for plotsquared plugin")
@LoadOrder(value = PluginLoadOrder.POSTWORLD)
@Author(value = "Stefan Biermann")
@Commands({
        @Command(name = "pmenu", desc = "Plotmenue Kommando", aliases = {"plotmenu", "plotmenue", "plotm"}, usage = "/<command>"),
        @Command(name = "pmreload", desc = "Reload config file", permission = "pmenu.admin", permissionMessage = "You do not have permission!")
})
@Permissions(@Permission(name = "pmenu.admin", desc = "Allows to reload the config of the plotmenu plugin", defaultValue = PermissionDefault.OP))
@ApiVersion(ApiVersion.Target.v1_15)
public class PlotMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getLogger().info("generating default config.yml");
            ConfigHandler.initConfig(getConfig());
            saveConfig();
        }
        PlotMenuGui plotMenuGui = new PlotMenuGui(this);

        this.getCommand("pmenu").setExecutor(new OpenGuiCommand(this, plotMenuGui));
        this.getCommand("pmreload").setExecutor(new ReloadConfigCommand(this));

        Bukkit.getPluginManager().registerEvents(plotMenuGui, this);

        getLogger().info("init complete");
    }

    @Override
    public void onDisable() {
        //nothing yet to do
    }


}
