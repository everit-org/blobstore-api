package org.everit.blobstore.api;

import java.io.InputStream;

public interface Blobstore {

  void deleteBlob(long blobId);

  /**
   * Getting the size of a blob by it's id.
   *
   * @param blobId
   *          The id of the blob.
   * @return The size in bytes.
   * @throws BlobstoreException
   *           if no blob found for {@code blobId} or there is an unexpected exception during
   *           getting the id of the blob.
   */
  long getBlobSizeByBlobId(long blobId);

  /**
   * Reading the content of a blob from the given position.
   *
   * @param blobId
   *          The unique id of the blob.
   * @param startPosition
   *          The position where the blob reading will be started from.
   * @param blobReader
   *          The {@link BlobReader#readBlob(InputStream)} function will be called to let the
   *          programmer read the content of the blob. The function has one InputStream parameter
   *          that should not be closed as it is handled automatically. This encapsulation is
   *          necessary to be sure that the current transaction, connection and resultSet is opened
   *          until the end of reading of the inputStream.
   * @throws BlobstoreException
   *           if a blob cannot be read due to one of the reasons
   */
  void readBlob(long blobId, long startPosition, BlobReader blobReader);

  /**
   * Storing a blob with the data coming from the given inputStream.
   *
   * @param blobStream
   *          The stream where data will be read from when the blob is stored.
   * @param length
   *          The length of data that is read from the stream or if null the input stream will be
   *          read until its end.
   * @return The unique id of this blob.
   * @throws BlobstoreException
   *           with the following reasons
   * @throws org.everit.util.core.validation.ValidationException
   *           if the blobStream parameter is null.
   */
  long storeBlob(InputStream blobStream, Long length);

}
