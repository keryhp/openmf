package uk.ac.openmf.services;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import uk.ac.openmf.model.OpenMFPhoto;
import uk.ac.openmf.model.OpenMFPhotoManager;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.web.ConfigManager;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileStat;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ImagesServiceFailureException;
import com.google.appengine.api.images.ServingUrlOptions;

/**
 * The main service class providing some helper method for photo management.
 *
 */
public class PhotoServiceManager {
  private static final Logger logger =
      Logger.getLogger(PhotoServiceManager.class.getCanonicalName());
  private ConfigManager configManager;
  private OpenMFPhotoManager photoManager;

  public PhotoServiceManager(ConfigManager configManager, OpenMFPhotoManager photoManager) {
    this.configManager = configManager;
    this.photoManager = photoManager;
  }

  public String getUploadUrl() {
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    UploadOptions uploadOptions = null;
    String bucket = configManager.getGoogleStorageBucket();
    if (bucket == null || bucket.isEmpty()) {
    	System.out.println("Yoohooo!");
      uploadOptions = UploadOptions.Builder.withDefaults();
    } else {
      uploadOptions = UploadOptions.Builder.withGoogleStorageBucketName(bucket);
    }
    return blobstoreService.createUploadUrl(configManager.getUploadHandlerUrl(), uploadOptions);
  }

  public String getThumbnailUrl(BlobKey blobKey) {
    ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
    options.imageSize(configManager.getPhotoThumbnailSizeInPixels());
    options.crop(configManager.isPhotoThumbnailCrop());
    try {
      return ImagesServiceFactory.getImagesService().getServingUrl(options);
    } catch (ImagesServiceFailureException e) {
      logger.severe("Failed to get image serving url: " + e.getMessage());
      return "";
    } catch (Exception e) {
      logger.severe("Invalid blob key: " + e.getMessage());
      return "";
    }
  }

  public String getPhotoDisplayUrl(BlobKey blobKey) {
    ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
    options.imageSize(configManager.getPhotoThumbnailSizeInPixels());
    options.crop(configManager.isPhotoThumbnailCrop());
    try {
      return ImagesServiceFactory.getImagesService().getServingUrl(options);
    } catch (ImagesServiceFailureException e) {
      logger.severe("Failed to get image serving url: " + e.getMessage());
      return "";
    }
  }

  public String getImageDownloadUrl(OpenMFPhoto photo) {
	    return new StringBuilder(configManager.getDownloadHandlerUrl()).append("?")
	        .append(OpenMFConstants.FIELD_NAME_ID)
	        .append("=")
	        .append(photo.getId())
	        .append("&amp;")
	        .append(OpenMFConstants.FIELD_NAME_TYPEID)
	        .append("=")
	        .append(photo.getTypeId()).toString();
	  }
  
  public void cleanDeatctivedPhotos() {
    Iterable<OpenMFPhoto> photos = photoManager.getDeactivedPhotos();
    if (photos != null) {
      for (OpenMFPhoto photo : photos) {
        removeDeactivedPhoto(photo);
      }
    }
  }
  private void removeDeactivedPhoto(OpenMFPhoto photo) {
    if (photo != null && !photo.isActive()) {
      try {
        FileService fileService = FileServiceFactory.getFileService();
        BlobKey blobKey = photo.getBlobKey();
        AppEngineFile file = fileService.getBlobFile(blobKey);
        FileStat stat = fileService.stat(file);
        if (stat != null) {
          logger.fine("photo:" + photo.getId() + " blob file stat is not null");
          BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
          blobstoreService.delete(blobKey);
          logger.info("The blob is deleted. try to delete the entity from datastore.");
          photoManager.deleteEntity(photo);
        }
      } catch (FileNotFoundException e) {
        logger.info("The blob is alrady deleted. try to delete the entity from datastore.");
        photoManager.deleteEntity(photo);
      } catch (Exception e) {
        logger.severe("Failed to delete the blob storge for photo " + photo.getId() +
            ":" + e.getMessage());
      }
    }
  }
}
