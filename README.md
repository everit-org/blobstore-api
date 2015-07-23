# blobstore-api

## Introduction

Blobstore API allows the user to write and read large binary data in a
transactional store. During the read and write operations, it is possible
to call seek() just as if the accessed data was a file.

## Usage

### Blob creation

    try(BlobAccessor blobAccessor = blobstore.createBlob()) {
      // Write the data
      blobAccessor.write(myDataByteArray, 0, myDataByteArray.length);
    }

### Updating a blob

    try(BlobAccessor blobAccessor = blobstore.updateBlob(blobId)) {
      // Seek to some place where we want to update
      blobAccessor.seek(10);
      // And do some update
      blobAccessor.write(myByteArray, 0, myByteArray.length);
    }

### Reading from a blob

    try(BlobReader blobReader = blobstore.readBlob(blobId)) {
      // Seek to a place where you want to read from
      blobReader.seek(15);
      blobAccessor.read(myByteArray, 0, myByteArray.length);
    }

### Other notes

_Blob_ operations must always be in JTA transaction. The best is to use
the [transaction-propagator-jta][5] project for this purpose.

_BlobAccessor_ inherits from _BlobReader_ so reading is possible during
updating or creating the _Blob_.

It is recommended not to open a Blob for longer time. In case a file is
uploaded from the Internet, it is recommended to use the Servlet Async API and
always write as much only as there is in the buffer, than close the
_BlobAccessor_. Next time data is available again on the HTTP channel, open a
_BlobAccessor_ again and seek to the end of the _Blob_ before writing the data
out from the buffer.

## Implementations

The following implementations are available for the API:

 - [blobstore-mem][1]: Stores blobs in the memory. This project is intended
   to use for unit tests.
 - [blobstore-jdbc][2]: The implementations stores large objects within
   relational databases by using the JDBC Blob interface.
 - [blobstore-cache][3]: A cache wrapper for any blobstore that stores
   frequently read blob chunks in memory.

## Testing

All _Blobstore_ implementations should be tested with the
[blobstore-testbase][4] project.

[1]: https://github.com/everit-org/blobstore-mem
[2]: https://github.com/everit-org/blobstore-jdbc
[3]: https://github.com/everit-org/blobstore-cache
[4]: https://github.com/everit-org/blobstore-testbase
[5]: https://github.com/everit-org/transaction-propagator-jta
