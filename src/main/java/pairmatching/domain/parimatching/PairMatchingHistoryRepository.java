package pairmatching.domain.parimatching;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.domain.crew.Crew;

public class PairMatchingHistoryRepository {

    private final List<PairMatchingHistory> histories = new ArrayList<>();

    public PairMatchingHistoryRepository() {
    }

    public void save(PairMatchingHistory history) {
        this.histories.add(history);
    }

    public Optional<PairMatchingHistory> findOne(PairMatchingRequest request) {
        return this.histories.stream()
                .filter(history -> history.matches(request.getCourse(), request.getMission()))
                .findFirst();
    }

    public void initialize() {
        clearCrewMetHistory();
        this.histories.clear();
    }

    private void clearCrewMetHistory() {
        this.histories.stream()
                .flatMap(history -> history.getPairs().stream())
                .flatMap(pair -> pair.getCrews().stream())
                .forEach(Crew::clearMetHistory);
    }

}
