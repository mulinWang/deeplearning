package com.mulin.deeplearning.neuralnetwork.loader

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/2.
  * 标签数据加载
  */
class LabelLoader(path: String, count: Int) extends AbstractLoader(path, count) {

  /**
    * 将单个标签数值转为10个byte的数组
    * @param label
    * @return
    */
  def norm(label: Int): ArrayBuffer[Double] = {
    val labelVec = ArrayBuffer.empty[Double]
    for(i <- 0 until 10) {
      if (i == label) labelVec += 0.9d
      else labelVec += 0.1d
    }
    labelVec
  }

  override def load: ArrayBuffer[ArrayBuffer[Double]] = {
    val labels = ArrayBuffer.empty[ArrayBuffer[Double]]
    val content = getFileContent()
    for (index <- 0 until count) {
      labels += norm(content(index + 8).toInt)
    }
    labels
  }
}
object LabelLoader {
  def apply(path: String, count: Int): AbstractLoader = new LabelLoader(path, count)
}
