package com.knoldus.readerWriterProblem

object ReaderWriterLockDriver extends App {

  private var sharedResource: Int = 0
  val lock = new ReaderWriterLock()

  // Start multiple readers and writers
  private val reader1 = new Thread {
    override def run(): Unit = {
      lock.withReadLock {
        println(s"Reader 1 read sharedResource: $sharedResource")
        Thread.sleep(1000)
      }
    }
  }
  private val reader2 = new Thread {
    override def run(): Unit = {
      lock.withReadLock {
        println(s"Reader 2 read sharedResource: $sharedResource")
        Thread.sleep(1000)
      }
    }
  }
  private val writer1 = new Thread {
    override def run(): Unit = {
      lock.withWriterLock {
        sharedResource += 1
        println(s"Writer 1 wrote sharedResource: $sharedResource")
        Thread.sleep(3000)
      }
    }
  }
  private val reader3 = new Thread {
    override def run(): Unit = {
      lock.withReadLock {
        println(s"Reader 3 read sharedResource: $sharedResource")
        Thread.sleep(1000)
      }
    }
  }
  private val writer2 = new Thread {
    override def run(): Unit = {
      lock.withWriterLock {
        sharedResource += 1
        println(s"Writer 2 wrote sharedResource: $sharedResource")
        Thread.sleep(3000)
      }
    }
  }

  writer2.start()
  writer1.start()
  reader1.start()
  reader2.start()
  reader3.start()

  // calling join to ensure all thread is executed before exiting main method
  reader1.join()
  reader2.join()
  writer1.join()
  reader3.join()
  writer2.join()
}