package com.mulin.deeplearning.neuralnetwork.loader

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/2.
  */
class TestLoader extends FunSuite {

  test("get file content") {
    val count = 10000
    val loader = new Loader("/t10k-images.idx3-ubyte", count)
    val byteArr = loader.getFileContent()
    println(s"byteArr length: ${byteArr.length}")
  }
}

class Loader(path: String, count: Int)  extends AbstractLoader(path, count) {
  override def load: ArrayBuffer[ArrayBuffer[Double]] = ???
}
