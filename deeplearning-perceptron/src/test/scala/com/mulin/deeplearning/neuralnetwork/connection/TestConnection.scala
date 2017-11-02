package com.mulin.deeplearning.neuralnetwork.connection

import org.scalatest.FunSuite
import org.slf4j.LoggerFactory

import scala.concurrent.forkjoin.ThreadLocalRandom

/**
  * Created by mulin on 2017/10/31.
  */
class TestConnection extends FunSuite {
  private[this] val log = LoggerFactory.getLogger(getClass)

  test("test random") {
    val weight: Float = ThreadLocalRandom.current().nextDouble(-1.0f, 1.0f).toFloat
    log.info("weight: {}", weight)
    assert(weight > -1.0f && weight < 1.0f)
  }
}
