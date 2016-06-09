package link.mcseu.badhuman.blocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;
import link.mcseu.badhuman.api.Author;
import link.mcseu.badhuman.api.BadModInitializationEvent;
import link.mcseu.badhuman.items.BadItems;
import link.mcseu.badhuman.util.RandomUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class DnaBlock extends AbstractBlock {
    private final List<UUID> authors;

    public DnaBlock() {
        this.authors = new ArrayList<>();
        setHardness(20/ 1.5f);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        final Random r = new Random(x * y * z);
        final int stack = r.nextInt(9) + 1;

        final ArrayList<ItemStack> item = new ArrayList<>();
        item.add(new ItemStack(BadItems.DNA_ITEM.unsafe(), stack));
        return item;
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        final float factor = RandomUtil.nextSecureFloat(0.1f);
        return super.getBlockHardness(world, x, y, z) * factor;
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
        final float hardness = super.getPlayerRelativeBlockHardness(player, world, x, y, z); 
        if (authors.contains(player.getUniqueID())) {
            return hardness * 10;
        }
        return hardness;
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @SubscribeEvent
    public void loadAuthors(BadModInitializationEvent ev) {
        Stream.of(ev.getModification().getAuthors()).map(Author::getId)
                .filter(Objects::nonNull)
                .forEach(authors::add);
    }
}