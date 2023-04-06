package com.knoldus.readerWriterProblem

import java.util.concurrent.locks.ReentrantReadWriteLock

class ReaderWriterLock {
  private val lock = new ReentrantReadWriteLock(true)

  def withReadLock[T](block: => T): Unit = {
    val readLock = lock.readLock()
    readLock.lock()
    try {
      block
    } finally {
      readLock.unlock()
    }
  }

  def withWriterLock[T](block: => T): Unit = {
    val writerLock = lock.writeLock()
    writerLock.lock()
    try {
      block
    } finally {
      writerLock.unlock()
    }
  }

}