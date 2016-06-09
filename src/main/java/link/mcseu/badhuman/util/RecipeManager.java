package link.mcseu.badhuman.util;

import link.mcseu.badhuman.util.api.BadItem;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Map;
import link.mcseu.badhuman.util.api.ForgeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static lombok.AccessLevel.PRIVATE;

@Accessors(chain = true)
@Data
@NoArgsConstructor(access = PRIVATE) // GSON
public final class RecipeManager {
    private final Map<Character, ItemStack> items = Maps.newHashMap();
    private ItemStack out;
    private String[] grid;
    private boolean shapeless;

    private RecipeManager(ItemStack out) {
        this.out = out;
    }

    // TODO: Use Lombok instead
    public RecipeManager setGrid(String... grid) {
        this.grid = grid;

        return this;
    }
    
    public RecipeManager assign(char c, ForgeType<Item> item) {
        return assign(c, item.unsafe());
    }

    public RecipeManager assign(char c, Item item) {
        items.putIfAbsent(c, new ItemStack(item));

        return this;
    }

    public void addRecipe() {
        if (shapeless) {
            GameRegistry.addShapelessRecipe(out, items.values().toArray());
            return;
        }

        GameRegistry.addShapedRecipe(out, generateParams());
    }

    public static RecipeManager create(ItemStack is) {
        return new RecipeManager(is);
    }

    public static RecipeManager create(Item item, int amount) {
        return create(new ItemStack(item, amount));
    }

    public static RecipeManager create(BadItem item, int amount) {
        return create(item.unsafe(), amount);
    }

    private Object[] generateParams() {
        final Object[] params = new Object[grid.length + items.size() * 2];
        int i = 0;

        // Add Grid 
        for(String line : grid) {
            params[i++] = line;
        }

        // Add assigned Items
        for(Map.Entry<Character, ItemStack> e : items.entrySet()) {
            params[i++] = e.getKey();
            params[i++] = e.getValue();
        }

        return params;
    }
}