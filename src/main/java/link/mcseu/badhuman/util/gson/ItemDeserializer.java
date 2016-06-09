package link.mcseu.badhuman.util.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import link.mcseu.badhuman.blocks.BadBlocks;
import link.mcseu.badhuman.items.BadItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public final class ItemDeserializer implements JsonDeserializer<Item> {
    @Override
    public Item deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
        if (json.isJsonPrimitive()) {
            return (Item) loadInventoryValue(json.getAsString());
        }

        throw new JsonParseException("Could not deserialize Item.");
    }

    static Object loadInventoryValue(String name) {
        // [IDENT|1][member|len-1]
        try {
            final Class<?> container = identifyContainer(name.charAt(0));
            return container.getField(name.substring(1)).get(null);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new JsonParseException(ex);
        }
    }

    private static Class<?> identifyContainer(char type) {
        switch (type) {
            case '@': return Items.class;
            case '!': return BadItems.class;
            case '#': return BadBlocks.class;

            default:
                throw new JsonParseException("Unknown Item Class: " + type);
        }
    }
}