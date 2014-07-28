package uk.ac.openmf.security;


import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;


public class OpenMFGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {
	private final String ADMIN = "ADMIN";
	private final String NEW_USER = "NEW_USER";
	private final String USER ="USER";
	private final String FIELD_OFFICER = "FIELD_OFFICER";
	private final String SUPERVISOR = "SUPERVISOR";
	private final String ACCOUNTANT = "ACCOUNTANT";
	private final String CLIENT = "CLIENT";
	private final String BRANCH_MANAGER = "BRANCH_MANAGER";
	private final String GENERAL_MANAGER = "GENERAL_MANAGER";
	private final String HIGH_LEVEL = "HIGH_LEVEL";

	public OpenMFGrantedAuthoritiesMapper() {
	}

	@Override
	public Collection<? extends GrantedAuthority> mapAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		Set roles = EnumSet.noneOf(AppRole.class); //empty EnumSet

		for (GrantedAuthority authority : authorities) {
			if (ADMIN.equals(authority.getAuthority())) {
				roles.add(AppRole.ADMIN);
			} else if (NEW_USER.equals(authority.getAuthority())) {
				roles.add(AppRole.NEW_USER);
			}else if (USER.equals(authority.getAuthority())) {
				roles.add(AppRole.USER);
			}else if (FIELD_OFFICER.equals(authority.getAuthority())) {
				roles.add(AppRole.FIELD_OFFICER);
			}else if (SUPERVISOR.equals(authority.getAuthority())) {
				roles.add(AppRole.SUPERVISOR);
			}else if (ACCOUNTANT.equals(authority.getAuthority())) {
				roles.add(AppRole.ACCOUNTANT);
			}else if (CLIENT.equals(authority.getAuthority())) {
				roles.add(AppRole.CLIENT);
			}else if (BRANCH_MANAGER.equals(authority.getAuthority())) {
				roles.add(AppRole.BRANCH_MANAGER);
			}else if (GENERAL_MANAGER.equals(authority.getAuthority())) {
				roles.add(AppRole.GENERAL_MANAGER);
			}else if (HIGH_LEVEL.equals(authority.getAuthority())) {
				roles.add(AppRole.HIGH_LEVEL);
			}
		}
		return roles;
	}
}


