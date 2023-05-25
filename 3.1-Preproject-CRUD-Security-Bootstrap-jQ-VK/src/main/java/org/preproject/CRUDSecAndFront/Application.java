package org.preproject.CRUDSecAndFront;

import org.preproject.CRUDSecAndFront.model.Role;
import org.preproject.CRUDSecAndFront.model.Status;
import org.preproject.CRUDSecAndFront.repository.RolesRepository;
import org.preproject.CRUDSecAndFront.repository.StatusRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RolesRepository roleRepository = context.getBean(RolesRepository.class);
		StatusRepository statusRepository = context.getBean(StatusRepository.class);

		roleRepository.save(new Role("ROLE_USER"));
		roleRepository.save(new Role("ROLE_ADMIN"));

//		statusRepository.save(new Status(true));
//		statusRepository.save(new Status(false));
		statusRepository.save(new Status("I_WANT_TO_BE_A_ADMIN", 1));
		statusRepository.save(new Status("OK", 2));
		statusRepository.save(new Status("NO", 3));
		statusRepository.save(new Status("NEW_ADMIN", 4));
		statusRepository.save(new Status("REFUSE_REQUEST", 5));
		statusRepository.save(new Status("BED_STATUS", 100));


//		SpringApplication.run(Application.class, args);
	}
}