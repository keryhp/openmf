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
package uk.ac.openmf.model;

/**
 * Runtime exception thrown during datastore operations.
 *
 * @author Michael Tang (ntang@google.com)
 */
public class OpenMFModelException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public OpenMFModelException() {
    super();
  }

  public OpenMFModelException(String message, Throwable cause) {
    super(message, cause);
  }

  public OpenMFModelException(String message) {
    super(message);
  }

  public OpenMFModelException(Throwable cause) {
    super(cause);
  }
}
