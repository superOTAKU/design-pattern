package otaku.lottery.domain.lottery.repository;

import otaku.lottery.lottery.domain.entity.Award;

import java.util.ArrayList;
import java.util.List;

import otaku.lottery.lottery.repository.AwardRepository;

public class InMemoryAwardRepository implements AwardRepository {
    private final List<Award> awards =new ArrayList<>();

    public void addAward(Award award) {
        awards.add(award);
    }

    @Override
    public List<Award> findAwardsByPoolId(Long awardPoolId) {
        return new ArrayList<>(awards);
    }
}
