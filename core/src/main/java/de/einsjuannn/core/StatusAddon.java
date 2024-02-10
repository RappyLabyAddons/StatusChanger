package de.einsjuannn.core;

import de.einsjuannn.core.command.StatusCommand;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class StatusAddon extends LabyAddon<StatusConfiguration> {

    @Override
    protected void enable() {
        this.registerSettingCategory();
        this.registerCommand(new StatusCommand());
    }

    @Override
    protected Class<StatusConfiguration> configurationClass() {
        return StatusConfiguration.class;
    }
}
