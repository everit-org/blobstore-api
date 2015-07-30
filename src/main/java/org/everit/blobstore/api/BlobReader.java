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

import java.io.Closeable;

/**
 * Blob accessor to read Blob content and related data.
 */
public interface BlobReader extends Closeable {

  /**
   * Closes all resources that has been created for this accessor instance. This method throws only
   * unchecked exceptions in case any issue occures during closing the accessor.
   */
  @Override
  void close();

  /**
   * Returns the id of the blob that this accessor belongs to.
   *
   * @return The id of the blob.
   */
  long getBlobId();

  /**
   * Returns the current position of the cursor in the <code>BLOB</code>.
   *
   * @return The current position of the cursor in the <code>BLOB</code>.
   */
  long getPosition();

  /**
   * Reads up to <code>len</code> bytes of data from the <code>BLOB</code> into an array of bytes.
   * An attempt is made to read as many as <code>len</code> bytes, but a smaller number may be read.
   * The number of bytes actually read is returned as an integer.
   *
   * <p>
   * This method blocks until input data is available, end of file is detected, or an exception is
   * thrown.
   *
   * <p>
   * If <code>len</code> is zero, then no bytes are read and <code>0</code> is returned; otherwise,
   * there is an attempt to read at least one byte. If no byte is available because the stream is at
   * end of file, the value <code>-1</code> is returned; otherwise, at least one byte is read and
   * stored into <code>b</code>.
   *
   * <p>
   * The first byte read is stored into element <code>b[off]</code>, the next one into
   * <code>b[off+1]</code>, and so on. The number of bytes read is, at most, equal to
   * <code>len</code>. Let <i>k</i> be the number of bytes actually read; these bytes will be stored
   * in elements <code>b[off]</code> through <code>b[off+</code><i>k</i><code>-1]</code>, leaving
   * elements <code>b[off+</code><i>k</i><code>]</code> through <code>b[off+len-1]</code>
   * unaffected.
   *
   * <p>
   * In every case, elements <code>b[0]</code> through <code>b[off]</code> and elements
   * <code>b[off+len]</code> through <code>b[b.length-1]</code> are unaffected.
   *
   * @param b
   *          the buffer into which the data is read.
   * @param off
   *          the start offset in array <code>b</code> at which the data is written.
   * @param len
   *          the maximum number of bytes to read.
   * @return the total number of bytes read into the buffer, or <code>-1</code> if there is no more
   *         data because the end of the <code>BLOB</code> has been reached.
   * @throws java.io.IOException
   *           If reading the content of the <code>BLOB</code> throws an exception.
   *
   * @throws NullPointerException
   *           If <code>b</code> is <code>null</code>.
   * @throws IndexOutOfBoundsException
   *           If <code>off</code> is negative, <code>len</code> is negative, or <code>len</code> is
   *           greater than <code>b.length - off</code>
   * @throws java.util.ConcurrentModificationException
   *           if the content of the blob was changed since the last read operation and the original
   *           content cannot be provided.
   */
  int read(byte[] b, int off, int len);

  /**
   * Sets the current position within the <code>BLOB</code>. If the current position is the same as
   * the value of <code>pos</code> parameter, this function does nothing.
   *
   * <p>
   * This is similar to the fseek() call in the standard C library. It allows you to have random
   * access to the large object.
   *
   * @param pos
   *          position within object from begining
   * @throws java.io.UncheckedIOException
   *           if a database-access error occurs.
   * @throws IndexOutOfBoundsException
   *           if the specified <code>pos</code> is less than zero or bigger than the
   *           {@link #getSize()} of the <code>BLOB</code>.
   */
  void seek(long pos);

  /**
   * Returns the size of the blob.
   *
   * @return The size of the blob in bytes.
   */
  long getSize();

  /**
   * Get the version of the blob. The version can be used for optimistic locking.
   *
   * @return The version of the Blob at the moment of opening the blob.
   */
  long getVersion();

}
