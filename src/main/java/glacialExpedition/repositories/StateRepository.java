package glacialExpedition.repositories;

import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateRepository implements Repository<State> {
    List<State> states;

    public StateRepository() {
        this.states = new ArrayList<>();
    }

    @Override
    public Collection<State> getCollection() {
        return states;
    }

    @Override
    public void add(State entity) {
        states.add(entity);
    }

    @Override
    public boolean remove(State entity) {
        return states.remove(entity);
    }

    @Override
    public State byName(String name) {
        return states.stream()
                .filter(st -> st.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
