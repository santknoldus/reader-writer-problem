package com.knoldus.readerWriterProblem

import java.util.concurrent.locks.ReentrantReadWriteLock

class ReaderWriterLock {
  private val lock = new ReentrantReadWriteLock(true)

  def withReadLock(read: => Unit): Unit = {
    val readLock = lock.readLock()
    readLock.lock()
    try {
      read
    } finally {
      readLock.unlock()
      println("Reader thread unlocked")
    }
  }

  def withWriterLock(write: => Unit): Unit = {
    val writerLock = lock.writeLock()
    writerLock.lock()
    try {
      write
    } finally {
      writerLock.unlock()
      println("Writer thread unlocked")
    }
  }
}