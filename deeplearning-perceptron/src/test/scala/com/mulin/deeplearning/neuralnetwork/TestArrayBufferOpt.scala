package com.mulin.deeplearning.neuralnetwork

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/1.
  */
class TestArrayBufferOpt extends FunSuite {

  val arrInt = ArrayBuffer(1, 2, 3, 4)
  /**
    * 测试
    * 取 arraybuffer 最后一个值
    */
  test("test array buffer last value") {
    val lastValue = arrInt.last
    println(s"last value: ${lastValue}")
    assert(3 == lastValue)
  }

  test("test array buffer slice") {
    val sliceArrInt = arrInt.slice(0, arrInt.length - 1)
    println(s"sliceArrInt: ${sliceArrInt.mkString}, arrInt: ${arrInt.mkString}")

    assert(sliceArrInt == ArrayBuffer(1, 2, 3))
  }

  test("test array buffer reverse") {
    val reverseArrInt = arrInt.reverse
    println(s"reverseArrInt: ${reverseArrInt.mkString}, arrInt: ${arrInt.mkString}")

    assert(reverseArrInt == ArrayBuffer(4, 3, 2, 1))
  }
}
