package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer {

    private static final double DEFAULT_ENERGY = 40;

    public GlacierExplorer(String name) {
        super(name, DEFAULT_ENERGY);
    }
}
