package com.mulin.deeplearning.neuralnetwork

import com.mulin.deeplearning.neuralnetwork.node.{AbstractNode, BiasNode, WeightNode}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by XingBaoBao on 2017/11/01.
  * 神经网络中的节点层对象ß
  * layerIndex：当前层游标
  * nodeCount：当前层节点数
  */
class Layer(layerIndex: Int, nodeCount: Int) {

  //节点数组
  val nodes: ArrayBuffer[AbstractNode] = {
    val emptyArr = ArrayBuffer.empty[AbstractNode]
    for ( i <- 0 until nodeCount) {
      emptyArr += WeightNode(layerIndex, i)
    }
    emptyArr += BiasNode(layerIndex, nodeCount)
    emptyArr
  }

  /**
    * 计算层节点的输出向量
    */
  def calculateNodeOutput(): Unit = {
    for (node <- nodes.slice(0, nodes.length -1)) {
      node.calculateOutput
    }
  }

  /**
    * 设置输入层的输出
    * @param data
    */
  def setInputLayerOutPut(data: ArrayBuffer[Double]): Unit = {
    for(i <- 0 until data.length) {
      nodes(i).setOutput(data(i))
    }
  }

  override def toString = s"Layer(nodes: ${nodes.mkString})"
}

object Layer {
  def apply(layerIndex: Int, nodeCount: Int): Layer = new Layer(layerIndex, nodeCount)
}