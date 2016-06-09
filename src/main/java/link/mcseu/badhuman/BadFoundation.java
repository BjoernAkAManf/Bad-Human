package link.mcseu.badhuman;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.IOException;
import java.util.stream.Stream;
import link.mcseu.badhuman.api.BadMod;
import link.mcseu.badhuman.api.BadModInitializationEvent;
import link.mcseu.badhuman.blocks.BadBlocks;
import link.mcseu.badhuman.items.BadItems;
import link.mcseu.badhuman.util.ForgeUtil;
import link.mcseu.badhuman.util.RecipeManager;

@Mod(modid = BadHuman.MODID, version = BadHuman.VERSION)
public final class BadFoundation {
    private BadMod mod;

    @EventHandler
    public void loadConfig(FMLPreInitializationEvent ev) throws IOException {
        mod = new BadModImpl();
    }

    @EventHandler
    public void initialize(FMLInitializationEvent ev) throws IOException {
        BadItems.ITEMS.stream()
                // Set Tab to our Item Tab
                .map(i ->  i.setTab(BadItems.TAB))
                // GameRegistry register Items
                .forEach(ForgeUtil::registerItem);

        // TODO: Support blocks as input
        BadBlocks.BLOCKS.stream().forEach(ForgeUtil::registerBlock);

        Stream.of(ForgeUtil.loadConfig("config/recipes", RecipeManager[].class))
                .forEach(RecipeManager::addRecipe);

        BadModInitializationEvent.post(mod);
    }
}