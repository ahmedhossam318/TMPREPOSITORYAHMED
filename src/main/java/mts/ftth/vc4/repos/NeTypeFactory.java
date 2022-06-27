package mts.ftth.vc4.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import mts.ftth.vc4.models.NeBoxAlarmJob;
import mts.ftth.vc4.models.NeCabinetAlarmJob;
import mts.ftth.vc4.models.NeGponAlarmJob;
import mts.ftth.vc4.models.NeGponCardAlarmJob;
import mts.ftth.vc4.models.NeGponPortAlarmJob;

public class NeTypeFactory {
	
	public Object getNeTypeObject(String neType,Object o) {		
		
		if (neType == null) {
			return null;
		}
		if (neType.equalsIgnoreCase("GPON")) {
			return new NeGponAlarmJob();

		} else if (neType.equalsIgnoreCase("CARD")) {
			return  new NeGponCardAlarmJob();

		} else if (neType.equalsIgnoreCase("PORT")) {
			return  new NeGponPortAlarmJob();
			
		} else if (neType.equalsIgnoreCase("CABINET")) {
			return  new NeCabinetAlarmJob();

		} else if (neType.equalsIgnoreCase("BOX")) {
			return  new NeBoxAlarmJob();
			
		} 

		return null;
	}

}
