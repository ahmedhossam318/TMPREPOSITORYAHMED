package mts.ftth.vc4.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import mts.ftth.vc4.models.Fault;

public interface FaultRepo extends JpaRepository<Fault, String>{

}
