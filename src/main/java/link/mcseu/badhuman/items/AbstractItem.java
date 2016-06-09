package link.mcseu.badhuman.items;

import link.mcseu.badhuman.BadHuman;
import link.mcseu.badhuman.util.api.BadItem;
import link.mcseu.badhuman.util.ForgeUtil;
import lombok.Getter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

class AbstractItem extends Item implements BadItem {
    @Getter private final String simpleName;

    public AbstractItem() {
        this.simpleName = ForgeUtil.formatType(getClass());
        init();
    }

    public AbstractItem(String simpleName) {
        this.simpleName = simpleName;
        init();
    }

    @Override
    public final String getModId() {
        return BadHuman.MODID;
    }

    @Override
    public final Item unsafe() {
        return this;
    }

    @Override
    public final BadItem setTab(CreativeTabs tab) {
        return (BadItem) setCreativeTab(tab);
    }

    private void init() {
        setUnlocalizedName(getModId() + "_" + simpleName);
        setTextureName(getModId() + ":" + simpleName);
    }
}