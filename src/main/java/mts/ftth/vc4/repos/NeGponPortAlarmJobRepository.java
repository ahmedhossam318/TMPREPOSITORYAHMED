package mts.ftth.vc4.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mts.ftth.vc4.models.NeGponPortAlarmJob;

public interface NeGponPortAlarmJobRepository extends JpaRepository<NeGponPortAlarmJob, Long>,AlarmJobRepository{
	@Query("SELECT b FROM NeGponPortAlarmJob b where b.vc4Id = :vc4Id and jobFlag = 1 and rownum = 1 order by b.jobNo desc")
	NeGponPortAlarmJob findAllByVc4Id(Long vc4Id);
}
