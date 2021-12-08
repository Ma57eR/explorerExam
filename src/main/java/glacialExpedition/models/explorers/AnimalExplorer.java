package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer {

    private static final double DEFAULT_ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, DEFAULT_ENERGY);
    }
}
