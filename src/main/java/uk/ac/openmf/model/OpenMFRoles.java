package uk.ac.openmf.model;

/**
 * The roles entity interface.
 *
 */
public interface OpenMFRoles extends OpenMFEntity {
  Long getId();

  long getTimestamp();

  void setTimestamp(long timestamp);

  String getRoleId();

  void setRoleId(String roleId);

  String getCreatedById();

  void setCreatedById(String userId);

  String getDescription();

  void setDescription(String description);

}
