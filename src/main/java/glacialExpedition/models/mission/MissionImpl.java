package glacialExpedition.models.mission;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {
//    private State state;
//    private Collection<Explorer> explorers;
//
//    public MissionImpl(State state, Collection<Explorer> explorers) {
//        this.state = state;
//        this.explorers = explorers;
//    }

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        //Взимаме експонатите за конкретния стейт
        Collection<String> stateExhibits = state.getExhibits();
        int retiredExplorers = 0;
        for (Explorer explorer : explorers) {
            //Ако няма кислород - минава на следващия
            if (!explorer.canSearch()) {
                retiredExplorers++;
                break;
            }
            //Ако няма повече за изследване -> минава на следващия
            if (stateExhibits.isEmpty()) {
                break;
            }
            for (String exhibit : stateExhibits) {
                //Използва кислород
                explorer.search();
                //Взима пробата и я слага в кашона
                explorer.getSuitcase().getExhibits().add(exhibit);
                //Махаме пробата от щата
                stateExhibits.remove(exhibit);
                if (stateExhibits.isEmpty()) {
                    break;
                }
            }

        }

    }
}
