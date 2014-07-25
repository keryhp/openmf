/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.openmf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

/**
 * A utility class.
 *
 */
public final class ServletUtils extends HttpServlet {
  public static final String REQUEST_PARAM_NAME_EVENT_OWNER_ID = "user";
  public static final String REQUEST_PARAM_NAME_EVENT_ID = "eventId";
  public static final String REQUEST_PARAM_NAME_PHOTO_ID = "id";
  public static final String REQUEST_PARAM_NAME_BOOKING_ID = "bookingId";
  public static final String REQUEST_PARAM_NAME_PRIVATE = "private";
  public static final String REQUEST_PARAM_NAME_COMMENT = "comment";
  public static final String REQUEST_PARAM_NAME_EVENT_TITLE = "eventTitle";
  public static final String REQUEST_PARAM_NAME_EVENT_DESCRIPTION = "description";
  public static final String REQUEST_PARAM_NAME_EVENT_LOCATION = "location";
  public static final String REQUEST_PARAM_NAME_EVENT_TIME = "eventTime";
  public static final String REQUEST_PARAM_NAME_EVENT_TIME_FROM = "eventTimeFrom";
  public static final String REQUEST_PARAM_NAME_EVENT_TIME_TO = "eventTimeTo";
  public static final String REQUEST_PARAM_NAME_ACTIVE = "active";
  public static final String REQUEST_PARAM_NAME_QUERY_STRING = "queryString";
  public static final String REQUEST_PARAM_NAME_EVENT_DOC = "EventDoc";
  public static final String REQUEST_PARAM_NAME_USER_ROLE = "role";
  public static final String REQUEST_PARAM_NAME_USER_NORMAL = "normal";
  public static final String REQUEST_PARAM_NAME_USER_ORGANIZER = "organizer";
  public static final String REQUEST_PARAM_NAME_USER_ADMIN = "admin";
  
  public static final String REQUEST_PARAM_NAME_TARGET_URL = "targetUrl";
  // The error code.
  public static final String REQUEST_PARAM_NAME_CODE = "code";
  public static final String[] USER_ICONS = new String[] {
    "img/users/ba.png",
    "img/users/dp.png",
    "img/users/ej.png",
    "img/users/jm.png",
    "img/users/kk.png",
    "img/users/mg.png",
    "img/users/xm.png",
  };

  private static final String DATE_FORMAT_STRING = "K:mma MMM d, yyyy z";

  private ServletUtils() {}

  /**
   * Creates a new {@code SimpleDateFormat}. Since the class is not
   * thread-safe, we wrap it with a method to create a new one on every
   * call.
   *
   * @return a {@code SimpleDateFormat} object.
   */
  private static SimpleDateFormat getDateFormat() {
    return new SimpleDateFormat(DATE_FORMAT_STRING);
  }

  public static String formatTimestamp(long timestamp) {
    return getDateFormat().format(new Date(timestamp));
  }

  /**
   * Convert and validate the string id is a valid photo id.
   *
   * @param id the string id.
   * @return the photo id as long integer; or null if string id is invalid.
   */
  public static Long validatePhotoId(String id) {
    try {
      if (id != null) {
        return Long.parseLong(id);
      }
    } catch (NumberFormatException e) {
      // NOP.
    }
    return null;
  }
  
  /**
   * Convert and validate the string id is a valid event id.
   *
   * @param id the string id.
   * @return the event id as long integer; or null if string id is invalid.
   */
  public static Long validateEventId(String id) {
    try {
      if (id != null) {
        return Long.parseLong(id);
      }
    } catch (NumberFormatException e) {
      // NOP.
    }
    return null;
  }


  /**
   * Gets the user icon image url.
   *
   * @param userId the user id string.
   * @return the url string.
   */
  public static String getUserIconImageUrl(String userId) {
    if (userId != null) {
      int hash = userId.hashCode();
      if (hash < 0) {
        hash = -hash;
      }
      return USER_ICONS[hash % USER_ICONS.length];
    }
    return "";
  }

  public static String getProtectedUserName(String nickname) {
    if (nickname != null) {
      int index = nickname.indexOf("@");
      if (index > 0) {
        nickname = nickname.substring(0, index);
      }
    }
    return nickname;
  }
}
