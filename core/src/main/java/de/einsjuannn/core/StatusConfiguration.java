package de.einsjuannn.core;

import net.labymod.api.Laby;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

@SpriteTexture("settings")
@ConfigName("settings")
public class StatusConfiguration extends AddonConfig {

  @SpriteSlot(size = 32)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SpriteSlot(size = 32, x = 4, y = 1)
  @SettingSection("support")
  @ButtonSetting
  @MethodOrder(after = "enabled")
  public void openDiscordLink() {
    Laby.labyAPI().minecraft().chatExecutor().openUrl("https://discord.gg/MQdJDwRbBm");
  }

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }
}
