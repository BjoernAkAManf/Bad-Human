package link.mcseu.badhuman.blocks;

import com.google.common.collect.ImmutableList;
import java.util.List;
import link.mcseu.badhuman.util.api.BadBlock;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BadBlocks {
    public final BadBlock DNA_BLOCK = new DnaBlock();

    public final List<BadBlock> BLOCKS = new ImmutableList.Builder<BadBlock>()
            .add(DNA_BLOCK)
            .build();
}