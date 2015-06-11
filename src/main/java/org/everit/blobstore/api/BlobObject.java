package org.everit.blobstore.api;

/**
 * Interface to manipulate blob data.
 */
public interface BlobObject {

  /**
   * Truncates the <code>BLOB</code> value that this <code>Blob</code> object represents to be
   * <code>len</code> bytes in length.
   *
   * <p>
   * <b>Note:</b> If the value specified for <code>pos</code> is greater then the length+1 of the
   * <code>BLOB</code> value then the behavior is undefined. Some JDBC drivers may throw a
   * <code>SQLException</code> (that is translated to <code>BlobStoreException</code>) while other
   * drivers may support this operation.
   *
   * @param len
   *          the length, in bytes, to which the <code>BLOB</code> value that this <code>Blob</code>
   *          object represents should be truncated
   */
  void truncate(long len);
}
