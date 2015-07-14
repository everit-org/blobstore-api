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

/**
 * A store that can hold large binary data.
 */
public interface Blobstore {

  /**
   * Creates a new <code>BLOB</code>.
   *
   * @return The accessor object to manipulate the blob content.
   */
  BlobAccessor createBlob();

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
   * @return The reader object that can be used to read the blob.
   * @throws NoSuchBlobException
   *           if there is no <code>BLOB</code> with the specified <code>id</code>.
   */
  BlobReader readBlob(long blobId);

  /**
   * Reads the Blob within a pessimistic lock. The lock will be released when the transaction ends.
   * This can be useful if the programmer wants to be sure that the content and version of the blob
   * is not changed by other transaction until this transaction ends. Use this method with care as
   * it will avoid parallel execution of the blob content!
   *
   * @param blobId
   *          The id of the blob.
   * @return The reader object that can be used to read the blob.
   * @throws NoSuchBlobException
   *           if there is no <code>BLOB</code> with the specified <code>id</code>.
   */
  BlobReader readBlobForUpdate(long blobId);

  /**
   * Updates the content of a <code>BLOB</code>. Based on the implementation, this function might
   * lock the <code>BLOB</code> for update until the end of the atomic transaction. The position of
   * the blob is on the byte with index zero when the updatingAction is called.
   *
   * @param blobId
   *          The <code>id</code> of the <code>BLOB</code> that should be modified.
   * @return The reader object that can be used to modify the blob content.
   * @throws NoSuchBlobException
   *           if there is no <code>BLOB</code> with the specified <code>id</code>.
   */
  BlobAccessor updateBlob(long blobId);

}
