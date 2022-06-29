package mts.ftth.vc4.repos;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mts.ftth.vc4.models.NeBoxAlarmJob;

public interface NeBoxAlarmJobRepository extends JpaRepository<NeBoxAlarmJob, Long>,AlarmJobRepository {
	@Query("SELECT b FROM NeBoxAlarmJob b where b.vc4Id = :vc4Id and jobFlag = 1 and rownum = 1 order by b.jobNo desc")
	NeBoxAlarmJob findAllByVc4Id(Long vc4Id);
	
	@Query("SELECT b FROM NeBoxAlarmJob b where b.vc4Id = :vc4Id order by b.jobNo desc")
	List<NeBoxAlarmJob> findBoxJobsByVc4Id(Long vc4Id);
     
}
