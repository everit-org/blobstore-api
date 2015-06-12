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

  /**
   * Creates a new <code>BLOB</code>.
   *
   * @param createAction
   *          The action that fills the new <code>BLOB</code> with content.
   * @return The id of the <code>BLOB</code>.
   */
  long createBlob(Consumer<BlobAccessor> createAction);

  /**
   * Deletes a <code>BLOB</code> permanently.
   *
   * @param blobId
   *          The id of the <code>BLOB</code> that should be deleted.
   * @throws NoSuchBlobException
   *           if there is no <code>BLOB</code> with the specified <code>id</code>.
   */
  void deleteBlob(long blobId);

  /**
   * Accessing a blob for reading.
   *
   * @param blobId
   *          The id of the blob.
   * @param readingAction
   *          The consumer of the blob content that should be implemented by the user of this
   *          function.
   * @throws NoSuchBlobException
   *           if there is no <code>BLOB</code> with the specified <code>id</code>.
   * @throws java.util.ConcurrentModificationException
   *           if the content of the blob is modified during reading its content.
   */
  void readBlob(long blobId, Consumer<BlobReader> readingAction);

  /**
   * Updates the content of a <code>BLOB</code>. Based on the implementation, this function might
   * lock the <code>BLOB</code> for update until the end of the atomic transaction.
   *
   * @param blobId
   *          The <code>id</code> of the <code>BLOB</code> that should be modified.
   * @param updatingAction
   *          The callback action that modifies the content of the <code>BLOB</code>.
   * @throws NoSuchBlobException
   *           if there is no <code>BLOB</code> with the specified <code>id</code>.
   */
  void updateBlob(long blobId, Consumer<BlobAccessor> updatingAction);

}
