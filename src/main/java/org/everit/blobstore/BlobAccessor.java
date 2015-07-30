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
package org.everit.blobstore;

/**
 * Blob content manipulation interface.
 */
public interface BlobAccessor extends BlobReader {

  /**
   * The new version that will be applied after the transaction of the <code>BLOB</code>
   * manipulation is commited. Accessing the <code>BLOB</code> always changes the
   * <code>version</code> even if the content of the <code>BLOB</code> has not been modified via the
   * {@link BlobAccessor}.
   *
   * @return The version that will be assigned to the <code>BLOB</code> after the new content is
   *         persisted.
   */
  long getNewVersion();

  /**
   * Truncates the <code>BLOB</code> value that this <code>Blob</code> object represents to be
   * <code>len</code> bytes in length.
   *
   * @param newLength
   *          the length, in bytes, to which the <code>BLOB</code> value that this <code>Blob</code>
   *          object represents should be truncated
   * @throws IllegalArgumentException
   *           if <code>newLength</code> is lower than zero, or bigger than the current size of the
   *           <code>BLOB</code> or {@link #getPosition()} is not in the range of the new length.
   */
  void truncate(long newLength);

  /**
   * Writes <code>len</code> bytes from the specified byte array starting at offset <code>off</code>
   * to this Blob. The general contract for <code>write(b, off, len)</code> is that some of the
   * bytes in the array <code>b</code> are written to the Blob in order; element <code>b[off]</code>
   * is the first byte written and <code>b[off+len-1]</code> is the last byte written by this
   * operation.
   *
   * <p>
   * The {@link #getPosition()} will be incremented with the amount of the bytes that are written to
   * the <code>BLOB</code>.
   *
   * @param b
   *          the data.
   * @param off
   *          the start offset in the data.
   * @param len
   *          the number of bytes to write.
   * @throws NullPointerException
   *           if b is <code>null</code>.
   * @throws IndexOutOfBoundsException
   *           if <code>off</code> is negative, or <code>len</code> is negative, or
   *           <code>off+len</code> is greater than the length of the array <code>b</code>.
   */
  void write(byte[] b, int off, int len);
}
