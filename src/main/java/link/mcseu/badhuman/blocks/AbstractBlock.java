package link.mcseu.badhuman.blocks;

import link.mcseu.badhuman.BadHuman;
import link.mcseu.badhuman.items.BadItems;
import link.mcseu.badhuman.util.ForgeUtil;
import link.mcseu.badhuman.util.api.BadBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

class AbstractBlock extends Block implements BadBlock {
    AbstractBlock() {
        super(Material.cake);
        final String name = getSimpleName();
        setBlockName(getModId() + "_" + name);
        setBlockTextureName(getModId() + ":" + name);
        setCreativeTab(BadItems.TAB);
    }

    @Override
    public final String getSimpleName() {
        return ForgeUtil.formatType(getClass());
    }

    @Override
    public final Block unsafe() {
        return this;
    }

    @Override
    public final String getModId() {
        return BadHuman.MODID;
    }
}