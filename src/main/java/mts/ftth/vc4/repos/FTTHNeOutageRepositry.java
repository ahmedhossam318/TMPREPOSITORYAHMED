package mts.ftth.vc4.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mts.ftth.vc4.models.FTTHNeOutage;

public interface FTTHNeOutageRepositry extends JpaRepository<FTTHNeOutage, Long> {
	FTTHNeOutage findAllByVc4Id(Long vc4Id);
	List<FTTHNeOutage> findAllByNeType(String neType);
}
