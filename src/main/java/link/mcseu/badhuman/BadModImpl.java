package link.mcseu.badhuman;

import java.io.IOException;
import link.mcseu.badhuman.api.Author;
import link.mcseu.badhuman.api.BadMod;
import link.mcseu.badhuman.util.ForgeUtil;

final class BadModImpl implements BadMod {
    private final Author[] authors;

    public BadModImpl() throws IOException {
        this.authors = ForgeUtil.loadConfig("config/authors", Author[].class);;
    }

    @Override
    public Author[] getAuthors() {
        return authors;
    }
}