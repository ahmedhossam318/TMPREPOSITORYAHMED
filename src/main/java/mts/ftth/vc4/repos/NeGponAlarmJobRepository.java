package mts.ftth.vc4.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import mts.ftth.vc4.models.NeGponAlarmJob;

public interface NeGponAlarmJobRepository extends JpaRepository<NeGponAlarmJob, Long>,AlarmJobRepository {
	@Query("SELECT b FROM NeGponAlarmJob b where b.vc4Id = :vc4Id and jobFlag = 1 and rownum = 1 order by b.jobNo desc")
	NeGponAlarmJob findAllByVc4Id(Long vc4Id);
	
	
	@Query("SELECT b FROM NeGponAlarmJob b where b.vc4Id = :vc4Id order by b.jobNo desc")
	List<NeGponAlarmJob> findGponJobsByVc4Id(Long vc4Id);

}
