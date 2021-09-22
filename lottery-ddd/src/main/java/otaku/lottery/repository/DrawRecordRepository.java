package otaku.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otaku.lottery.entity.DrawRecord;

@Repository
public interface DrawRecordRepository extends JpaRepository<DrawRecord, Long> {
}
