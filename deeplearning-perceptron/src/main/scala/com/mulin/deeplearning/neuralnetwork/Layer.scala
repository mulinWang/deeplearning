package com.mulin.deeplearning.neuralnetwork

import scala.collection.mutable.ArrayBuffer

/**
  * Created by XingBaoBao on 2017/11/01.
  * 神经网络中的节点层对象ß
  * layerIndex：当前层游标
  * nodeCount：当前层节点数
  */
class Layer(layerIndex: Int, nodeCount: Int) {

  //节点数组
  val nodes = {
    val emptyArr = ArrayBuffer.empty[Node]
    for ( i <- 0 until nodeCount) {
      emptyArr += Node(layerIndex, i)
    }
    emptyArr


  }


  /**
    * 设置输入层的输出
    * @param data
    */
  def setInputLayerOutPut(data: ArrayBuffer[Float]): Unit = {
    for(i <- 0 until data.length) {
      nodes(i).setOutput(data(i))
    }
  }
}
