package com.mulin.deeplearning.neuralnetwork.other

import breeze.numerics.pow
import org.scalatest.FunSuite

/**
  * Created by mulin on 2017/11/1.
  */
class TestMathOpt extends FunSuite {

  test("test pow") {
    val y1 = pow(1, 10)
    assert(y1 == 1)

    val y2 = pow(2, 3)
    assert(y2 == 8)
  }

  test("test percent value") {
    val d =   0.11d
    println(s"percent d: ${(d * 100)}%")
  }
}
