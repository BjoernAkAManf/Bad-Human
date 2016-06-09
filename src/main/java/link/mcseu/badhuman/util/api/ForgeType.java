package link.mcseu.badhuman.util.api;

public interface ForgeType<T> {
    String getSimpleName();
    String getModId();

    // May return itself
    T unsafe();
}