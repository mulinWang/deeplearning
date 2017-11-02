package com.mulin.deeplearning.neuralnetwork.loader

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/2.
  */
class TestImageLoader extends FunSuite{

  //加载样本验证样本数是否与count相等
  test("test load") {
    val count = 10000
    val imageLoader = ImageLoader("/t10k-images.idx3-ubyte", count)
    val sample: ArrayBuffer[ArrayBuffer[Double]] = imageLoader.load

    println(s"sample length: ${sample.length}")
    assert(count == sample.length)
  }


}