package link.mcseu.badhuman.api;

import java.util.UUID;
import lombok.Data;

@Data
public final class Author {
    private final String name;
    private final UUID id;
}