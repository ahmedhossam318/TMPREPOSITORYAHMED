package mts.ftth.vc4.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;


@Configuration
public class AlarmFactoryRepo {
	
	@Autowired
	NeGponCardAlarmJobRepository cardRepo;
	@Autowired
	NeGponAlarmJobRepository gponRepo ;
	@Autowired
	NeGponPortAlarmJobRepository portRepo;
	@Autowired
	NeCabinetAlarmJobRepository cabinetRepo ;
	@Autowired
	NeBoxAlarmJobRepository boxRepo ;
	
	public JpaRepository getNeAlarm(String neType) {		
		System.out.println("neType IN FACTORY:"+neType);
	if (neType == null) {
		return null;
	}
	if (neType.equalsIgnoreCase("GPON")) {
		return gponRepo;

	} else if (neType.equalsIgnoreCase("CARD")) {
		return  cardRepo;

	} else if (neType.equalsIgnoreCase("PORT")) {
		return  portRepo;
		
	} else if (neType.equalsIgnoreCase("CABINET")) {
		return  cabinetRepo;

	} else if (neType.equalsIgnoreCase("BOX")) {
		return  boxRepo;
		
	} 

	return null;
}

}
