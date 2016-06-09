package link.mcseu.badhuman.util.api;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface BadItem extends ForgeType<Item> {
    BadItem setTab(CreativeTabs tab);
}