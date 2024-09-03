package com.andyg.pokeapi;

import com.andyg.pokeapi.persistence.entity.users.PermissionEntity;
import com.andyg.pokeapi.persistence.entity.users.RoleEntity;
import com.andyg.pokeapi.persistence.entity.users.RoleEnum;
import com.andyg.pokeapi.persistence.entity.users.UserEntity;
import com.andyg.pokeapi.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class PokeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeapiApplication.class, args);
	}


	//Insersiones en la base de datos para uso de pruebas con spring security
	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			//Create permissionS
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			//Create Role
			RoleEntity adminRole = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity userRole = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity invitedRole = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity developerRole = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission,
							refactorPermission))
					.build();

			//Create users
			UserEntity userAndy = UserEntity.builder()
					.username("usuariodepruebas")
					.password("1234")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(adminRole))
					.build();


			userRepository.saveAll(List.of(userAndy));
		};

	}
}
