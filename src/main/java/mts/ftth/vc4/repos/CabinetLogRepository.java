package mts.ftth.vc4.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import mts.ftth.vc4.models.CabinetLog;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetLogRepository extends JpaRepository<CabinetLog, Long> {

}
