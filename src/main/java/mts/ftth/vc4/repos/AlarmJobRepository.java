package mts.ftth.vc4.repos;


import java.util.List;

import mts.ftth.vc4.models.Element;


public interface AlarmJobRepository {
	Element findAllByVc4Id(Long vc4Id);
	 <S extends   Element > S save(S entity);
}
