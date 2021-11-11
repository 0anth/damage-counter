package com.damagecounter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("damagecounter")
public interface DamageCounterConfig extends Config {
    @ConfigItem(
            keyName = "sendToChat",
            name = "Display in chat log",
            description = "Display details in chat log after each kill"
    )
    default boolean sendToChat() {
        return true;
    }

    @ConfigItem(
            keyName = "showDamage",
            name = "Show damage",
            description = "Show total damage instead of DPS"
    )
    default boolean showDamage() {
        return true;
    }

    @ConfigItem(
            keyName = "overlayAutoHide",
            name = "Automatically hide overlay",
            description = "Automatically hide the overlay when the boss dies"
    )
    default boolean overlayAutoHide() {
        return true;
    }

    @ConfigItem(
            keyName = "overlayHide",
            name = "Always hide overlay",
            description = "Always hide the overlay"
    )
    default boolean overlayHide() {
        return false;
    }

    @ConfigItem(
            keyName = "additionalNpcs",
            name = "Additional NPCs",
            description = "Also track these NPCs, comma separated"
    )
    String additionalNpcs();

    @ConfigItem(
            keyName = "resetOnTeleport",
            name = "Reset on Teleport",
            description = "Reset the counter anytime the player teleports"
    )

    default boolean resetOnTeleport() {
        return false;
    }

    @ConfigItem(
            keyName = "resetOnPlayerDeath",
            name = "Reset on Player Death",
            description = "Reset the counter anytime the player dies"
    )

    default boolean resetOnPlayerDeath() {
        return false;
    }

}
