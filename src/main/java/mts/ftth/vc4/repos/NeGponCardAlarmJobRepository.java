package mts.ftth.vc4.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mts.ftth.vc4.models.NeGponCardAlarmJob;

public interface NeGponCardAlarmJobRepository extends JpaRepository<NeGponCardAlarmJob, Long>,AlarmJobRepository{
	@Query("SELECT b FROM NeGponCardAlarmJob b where b.vc4Id = :vc4Id and jobFlag = 1 and rownum = 1 order by b.jobNo desc")
	NeGponCardAlarmJob findAllByVc4Id(Long vc4Id);
	
	@Query("SELECT b FROM NeGponCardAlarmJob b where b.vc4Id = :vc4Id order by b.jobNo desc")
	List<NeGponCardAlarmJob> findGponCardJobsByVc4Id(Long vc4Id);

}
