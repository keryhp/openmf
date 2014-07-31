package uk.ac.openmf.model;

/**
 * The photo entity manager interface.
 *
 */
public interface OpenMFPhotoManager extends OpenMFEntityManager<OpenMFPhoto> {

  OpenMFPhoto getPhoto(Long typeId, long id);
  
  Iterable<OpenMFPhoto> getPhoto(Long typeId);

  Iterable<OpenMFPhoto> getAllPhotos(Long typeId);

  Iterable<OpenMFPhoto> getDeactivedPhotos();

  Iterable<OpenMFPhoto> getActivePhotos();

  OpenMFPhoto newPhoto(Long typeId);

  OpenMFPhoto deactivePhoto(Long typeId, long id);
}
