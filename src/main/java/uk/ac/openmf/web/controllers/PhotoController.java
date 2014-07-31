package uk.ac.openmf.web.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFPhoto;
import uk.ac.openmf.model.OpenMFPhotoManager;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;


@Controller
public class PhotoController {


	@RequestMapping(value="/download.htm", method = RequestMethod.GET)
	public void downloadphoto(HttpServletRequest req, HttpServletResponse res) throws IOException {
	    Long typeId = ServletUtils.validatePhotoId(req.getParameter("typeId"));
	    String id = req.getParameter("id");
	    Long photoId = ServletUtils.validatePhotoId(id);
	    if (photoId != null && typeId != null) {
	      OpenMFPhoto photo = AppContext.getAppContext().getPhotoManager().getPhoto(typeId, photoId);
	      BlobKey blobKey = photo.getBlobKey();
	      BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	      blobstoreService.serve(blobKey, res);
	    } else {
	      res.sendError(400, "One or more parameters are not set");
	    }
	}

	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String uploadphoto(HttpServletRequest req) throws NoSuchAlgorithmException, InvalidKeySpecException {
		AppContext appContext = AppContext.getAppContext();
		OpenMFUser currentUser = (OpenMFUser)appContext.getCurrentUser();
		if (currentUser == null) {
			return null;
		}
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		String type = req.getParameter("type");
		String typeId = null;
		String respurl = null;
		if("user".equalsIgnoreCase(type)){
			typeId = req.getParameter("userId");
			respurl = "redirect:/admin/users.htm";
		}else{
			typeId = req.getParameter("clientId");
			respurl = "redirect:/clients.htm";
		}
		List<BlobKey> keys = blobs.get("photo");
		String id = null;
		boolean succeeded = false;
		if (keys != null && keys.size() > 0) {
			OpenMFPhotoManager photoManager = appContext.getPhotoManager();
			OpenMFPhoto photo = photoManager.newPhoto(ServletUtils.validateEventId(typeId));
			photo.setActive(true);
			photo.setCreatedById(currentUser.getEmail());
			photo.setTimestamp(System.currentTimeMillis());
			photo.setType(type);
			photo.setTypeId(ServletUtils.validateEventId(typeId));
			BlobKey blobKey = keys.get(0);
			photo.setBlobKey(blobKey);
			photo = photoManager.upsertEntity(photo);
			id = photo.getId().toString();
			succeeded = true;
		}
		return respurl;
	}
}
