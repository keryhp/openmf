package uk.ac.openmf.users;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.openmf.security.AppRole;
import uk.ac.openmf.utils.OpenMFConstants;

import java.util.*;

/**
 * UserRegistry implementation which uses GAE's low-level Datastore APIs.
 *
 * @author harish
 */
public class GaeDatastoreUserRegistry implements UserRegistry {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public GaeUser findUser(String userId) {
        Key key = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        try {
            Entity user = datastore.get(key);

            long binaryAuthorities = (Long)user.getProperty(OpenMFConstants.FIELD_NAME_USER_AUTHORITIES);
            Set<AppRole> roles = EnumSet.noneOf(AppRole.class);

            for (AppRole r : AppRole.values()) {
                if ((binaryAuthorities & (1 << r.getBit())) != 0) {
                    roles.add(r);
                }
            }

            GaeUser gaeUser = new GaeUser(
                    user.getKey().getName(),
                    (String)user.getProperty(OpenMFConstants.FIELD_NAME_USERNAME),
                    (String)user.getProperty(OpenMFConstants.FIELD_NAME_EMAIL),
                    (String)user.getProperty(OpenMFConstants.FIELD_NAME_FORENAME),
                    (String)user.getProperty(OpenMFConstants.FIELD_NAME_SURNAME),
                    roles,
                    (Boolean)user.getProperty(OpenMFConstants.FIELD_NAME_ENABLED));

            return gaeUser;

        } catch (EntityNotFoundException e) {
            logger.debug(userId + " not found in datastore");
            return null;
        }
    }

    public void registerUser(GaeUser newUser) {
        logger.debug("Attempting to create new user " + newUser);

        Key key = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, newUser.getUserId());
        Entity user = new Entity(key);
        user.setProperty(OpenMFConstants.FIELD_NAME_EMAIL, newUser.getEmail());
        user.setProperty(OpenMFConstants.FIELD_NAME_USERNAME, newUser.getNickname());
        user.setProperty(OpenMFConstants.FIELD_NAME_FORENAME, newUser.getForename());
        user.setProperty(OpenMFConstants.FIELD_NAME_SURNAME, newUser.getSurname());
        user.setUnindexedProperty(OpenMFConstants.FIELD_NAME_ENABLED, newUser.isEnabled());

        Collection<AppRole> roles = newUser.getAuthorities();

        long binaryAuthorities = 0;

        for (AppRole r : roles) {
            binaryAuthorities |= 1 << r.getBit();
        }

        user.setUnindexedProperty(OpenMFConstants.FIELD_NAME_USER_AUTHORITIES, binaryAuthorities);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(user);
    }

    public void removeUser(String userId) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);

        datastore.delete(key);
    }
}
