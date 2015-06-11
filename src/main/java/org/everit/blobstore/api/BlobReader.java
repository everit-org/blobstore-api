package org.everit.blobstore.api;

import java.io.InputStream;

/**
 * Functional interface to read the content of blobs.
 */
public interface BlobReader {
  /**
   * A callback interface which can be used to read a stream.
   *
   * @param blobStream
   *          The input stream to be read.
   * @throws BlobstoreException
   *           If the reading of the blob cannot be executed.
   */
  void readBlob(InputStream blobStream);

}
