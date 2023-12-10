package pairmatching.domain.parimatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.CrewRepository;

public class PairMatcher {

    private static final int MAX_RETRY_COUNT = 3;

    private final CrewRepository crewRepository;
    private final PairMatchingHistoryRepository matchingHistoryRepository;

    public PairMatcher(CrewRepository crewRepository, PairMatchingHistoryRepository matchingHistoryRepository) {
        this.crewRepository = crewRepository;
        this.matchingHistoryRepository = matchingHistoryRepository;
    }

    public PairMatchingHistory match(PairMatchingRequest request) {
        List<String> crewNames = toCrewNames(crewRepository.getCrewsBy(request.getCourse()));
        int retryCount = MAX_RETRY_COUNT;

        while (retryCount > 0) {
            List<String> shuffledCrew = new ArrayList<>(Randoms.shuffle(crewNames));
            List<Pair> pairs = createNewPairs(shuffledCrew, request.getCourse());

            if (hasPairsMet(request, pairs)) {
                retryCount--;
                continue;
            }
            return createMatchingHistory(request, pairs);
        }

        throw new IllegalArgumentException("매칭 기회를 모두 소진했습니다.");
    }

    private PairMatchingHistory createMatchingHistory(PairMatchingRequest request, List<Pair> pairs) {
        pairs.forEach(pair -> pair.addMetHistory(request.getMission()));
        PairMatchingHistory history = new PairMatchingHistory(request.getCourse(), request.getMission(), pairs);
        matchingHistoryRepository.save(history);

        return history;
    }

    private List<Pair> createNewPairs(List<String> shuffledCrew, Course course) {
        List<Pair> pairs = new ArrayList<>();

        while (!shuffledCrew.isEmpty()) {
            if (shuffledCrew.size() == 3 || shuffledCrew.size() == 2) {
                pairs.add(new Pair(crewRepository.findAllBy(course, shuffledCrew)));
                shuffledCrew.clear();
                continue;
            }
            addNewPair(shuffledCrew, pairs, course);
        }

        return pairs;
    }

    private boolean hasPairsMet(PairMatchingRequest request, List<Pair> pairs) {
        return pairs.stream()
                .anyMatch(pair -> pair.hasMet(request.getMission()));
    }

    private void addNewPair(List<String> shuffledCrew, List<Pair> pairs, Course course) {
        int firstIndex = 0;
        int secondIndex = 1;

        Crew first = crewRepository.getBy(course, shuffledCrew.get(firstIndex));
        Crew second = crewRepository.getBy(course, shuffledCrew.get(secondIndex));

        pairs.add(Pair.of(first, second));

        shuffledCrew.remove(firstIndex);
        shuffledCrew.remove(firstIndex);
    }

    private List<String> toCrewNames(List<Crew> crews) {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

}
