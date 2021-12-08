package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    //Филдове от интерфейси
    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;
    private int exploredStates = 0;

    public ControllerImpl() {
        explorerRepository = new ExplorerRepository();
        stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer currentExplorer;
        switch (type) {
            case "AnimalExplorer":
                currentExplorer = new AnimalExplorer(explorerName);
                break;
            case "NaturalExplorer":
                currentExplorer = new NaturalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                currentExplorer = new GlacierExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(currentExplorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State currentState = new StateImpl(stateName);
        Collection<String> exhCollection = currentState.getExhibits();
        Collections.addAll(exhCollection, exhibits);
        this.stateRepository.add(currentState);

        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer currentExplorer = explorerRepository.byName(explorerName);

        if (currentExplorer.getName() != null) {
            return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
        }
        return String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State currentState = this.stateRepository.getCollection().stream().filter(s -> s.getName().equals(stateName)).findFirst().orElse(null);
        Collection<Explorer> explorers = this.explorerRepository.getCollection().stream().filter(ex -> ex.getEnergy() > 50).collect(Collectors.toList());
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        int explorersBefore = explorers.size();
        Mission exploring = new MissionImpl();
        exploring.explore(currentState, explorers);
        int explorersAfter = explorers.size();
        exploredStates++;
        return String.format(ConstantMessages.STATE_EXPLORER, currentState.getName(), explorersBefore-explorersAfter );
    }

    @Override
    public String finalResult() {


    return null;

    }
}
