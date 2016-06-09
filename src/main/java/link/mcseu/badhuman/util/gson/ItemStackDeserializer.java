package link.mcseu.badhuman.util.gson;

import com.google.common.base.Preconditions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class ItemStackDeserializer implements JsonDeserializer<ItemStack> {
    @Override
    public ItemStack deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
        final String[] args = parse(json.getAsString());
        final int amount = Integer.parseInt(args[1]);
        final Object val = ItemDeserializer.loadInventoryValue(args[0]);
        Preconditions.checkArgument(val instanceof Item || val instanceof Block);

        if(val instanceof Item) {
            return new ItemStack((Item) val, amount);
        }

        return new ItemStack((Block) val, amount);
    }

    private String[] parse(String json) {
        if(json.contains("|")) {
            return json.split("\\|", 2);
        }
        return new String[]{json, "1"};
    }
}