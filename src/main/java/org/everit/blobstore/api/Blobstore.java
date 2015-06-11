/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.blobstore.api;

import java.util.function.Consumer;

/**
 * A store that can hold large binary data.
 */
public interface Blobstore {

  long createBlob(Consumer<BlobAccessor> createAction);

  void deleteBlob(long blobId);

  /**
   * Getting the size of a blob by its id.
   *
   * @param blobId
   *          The id of the blob.
   * @return The size in bytes.
   * @throws BlobstoreException
   *           if no blob found for {@code blobId} or there is an unexpected exception during
   *           getting the id of the blob.
   */
  long getBlobSizeById(long blobId);

  /**
   * Accessing a blob for reading.
   *
   * @param blobId
   *          The id of the blob.
   * @param readingAction
   *          The consumer of the blob content that should be implemented by the user of this
   *          function.
   * @throws BlobstoreException
   *           if a blob cannot be read due to one of the reasons
   */
  void readBlob(long blobId, Consumer<BlobReader> readingAction);

  void updateBlob(long blobId, Consumer<BlobAccessor> updatingAction);

}
