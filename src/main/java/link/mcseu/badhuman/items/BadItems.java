package link.mcseu.badhuman.items;

import com.google.common.collect.ImmutableList;
import java.util.List;
import link.mcseu.badhuman.BadHuman;
import link.mcseu.badhuman.util.api.BadItem;
import link.mcseu.badhuman.util.ForgeUtil;
import lombok.experimental.UtilityClass;
import net.minecraft.creativetab.CreativeTabs;

@UtilityClass
public class BadItems {
    public final BadItem DNA_ITEM = new AbstractItem("dna_item");
    public final BadItem THE_PICK = new AbstractItem("the_pick");
    public final CreativeTabs TAB = ForgeUtil.newTab(BadHuman.MODID, DNA_ITEM);

    public final List<BadItem> ITEMS = new ImmutableList.Builder<BadItem>()
            .add(DNA_ITEM)
            .add(THE_PICK)
            .build();
}