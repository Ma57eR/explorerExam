package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{

    private static final double DEFAULT_ENERGY = 60;


    public NaturalExplorer(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void search() {
        if (getEnergy() <= 7) {
            this.setEnergy(0);
        } else {
         this.setEnergy(this.getEnergy() - 7);
        }
    }


}
