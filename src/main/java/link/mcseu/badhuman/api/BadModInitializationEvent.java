package link.mcseu.badhuman.api;

import cpw.mods.fml.common.eventhandler.Event;
import lombok.RequiredArgsConstructor;
import net.minecraftforge.common.MinecraftForge;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
public final class BadModInitializationEvent extends Event {
    private final BadMod mod;

    public BadMod getModification() {
        return mod;
    }

    public static void post(BadMod mod) {
        MinecraftForge.EVENT_BUS.post(new BadModInitializationEvent(mod));
    }
}