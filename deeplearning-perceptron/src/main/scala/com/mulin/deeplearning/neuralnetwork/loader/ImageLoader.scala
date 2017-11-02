package com.mulin.deeplearning.neuralnetwork.loader

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/2.
  * 图像数据加载
  */
class ImageLoader(path: String, count: Int) extends AbstractLoader(path, count) {

  /**
    * 获取图片向量
    * @param content
    * @param index
    * @return
    */
  def getPic(content: Array[Byte], index: Int): ArrayBuffer[ArrayBuffer[Double]] = {
    //文件字节数组游标
    val start = index * 28 * 28 + 16
    //图片二维数组
    val picture = ArrayBuffer.empty[ArrayBuffer[Double]]

    for(i <- 0 until 28) {
      picture += ArrayBuffer.empty[Double]
      for (j <- 0 until 28) {
        picture(i) += content(start + (i * 28) + j)
      }
    }
    picture
  }

  /**
    * 获取单个样本
    * @param picture
    * @return
    */
  def getOneSample(picture: ArrayBuffer[ArrayBuffer[Double]]): ArrayBuffer[Double] = {
    val sample = ArrayBuffer.empty[Double]
    for (i <- 0 until 28) {
      for (j <- 0 until 28) {
        sample += picture(i)(j)
      }
    }
    sample
  }

  /**
    * 加载图像数据并转化为byte数组
    * @return
    */
  override def load: ArrayBuffer[ArrayBuffer[Double]] = {
    val dataSet = ArrayBuffer.empty[ArrayBuffer[Double]]
    val content = getFileContent()
    for (index <- 0 until count) {
      dataSet += getOneSample(getPic(content, index))
    }
    dataSet
  }
}

object ImageLoader {
  def apply(path: String, count: Int): AbstractLoader = new ImageLoader(path, count)
}

