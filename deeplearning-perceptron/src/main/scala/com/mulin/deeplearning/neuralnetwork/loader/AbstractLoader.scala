package com.mulin.deeplearning.neuralnetwork.loader

import better.files._

import scala.collection.mutable.ArrayBuffer


/**
  * Created by mulin on 2017/11/2.
  */
abstract class AbstractLoader(path: String, count: Int) {

  /**
    * 获取文件并转化为byte数组
    * @return
    */
  def getFileContent(): Array[Byte] = {
    val file = File(path)
    val byte: Array[Byte] = file.loadBytes
    byte
  }

  def load:ArrayBuffer[ArrayBuffer[Double]]
}
