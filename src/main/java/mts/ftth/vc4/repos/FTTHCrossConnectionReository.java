package mts.ftth.vc4.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mts.ftth.vc4.models.FTTHCrossConnection;

@Repository
public interface FTTHCrossConnectionReository extends JpaRepository<FTTHCrossConnection, Long> {

}
