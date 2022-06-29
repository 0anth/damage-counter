package com.damagecounter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.NPC;
import net.runelite.client.util.Text;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
public class DamageCounterState {
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private String npcName = null;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private NPC barrowsNpc = null;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private int boss = 0;

    @Getter(AccessLevel.PACKAGE)
    private final Map<String, DamageMember> members = new ConcurrentHashMap<>();
    @Getter(AccessLevel.PACKAGE)
    private final DamageMember total = new DamageMember("Total");


    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private List<String> additionalNpcs = Collections.emptyList();

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private final Runnable resetDamage = () -> {
        for (DamageMember damageMember : members.values()) {
            damageMember.reset();
        }
        total.reset();
    };

    void addAdditionalNpcsFromString(String npcsList) {
        additionalNpcs = npcsList != null ? Text.fromCSV(npcsList) : Collections.emptyList();
    }

    void setEndTime() {
        Instant endTime = Instant.now();
        total.setEnd(endTime);
        for (DamageMember damageMember : members.values()) {
            damageMember.setEnd(endTime);
        }
    }

    void reset(int delay) {
        barrowsNpc = null;
        boss = 0;
        npcName = null;

        if (delay <= 0) {
            scheduler.execute(resetDamage);
        } else {
            scheduler.schedule(resetDamage, delay, TimeUnit.SECONDS);
        }
    }
}
