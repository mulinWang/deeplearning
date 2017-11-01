package com.mulin.deeplearning.neuralnetwork.node

import breeze.numerics.exp


/**
  * Created by XingBaoBao on 2017/10/30.
  * 神经网络中的权重节点对象
  */

class WeightNode(layerIndex: Int, nodeIndex: Int) extends AbstractNode(layerIndex, nodeIndex) {

  /**
    * 计算节点输出
    * y = sigmoid(∑a * w)
    */
  override def calculateOutput(): Unit ={
    val output =  upStream.map(conn => conn.upStreamNode.output * conn.weight)
      .reduce(_+_)
    setOutput(sigmoid(output))
  }

  /**
    * 计算输出层节点 delta
    * δ = y * (1-y) * (t-y)
    * @param label
    */
  override def calculateOutputLayerDelta(label: Double): Unit = {
    setDelta(output * (1 - output) * (label - output))
  }

  /**
    * sigmoid 函数（激活函数）
    * @param x
    * @return
    */
  def sigmoid(x: Double): Double = (1.0 / (exp(x * -1.0) + 1.0))

  override def toString = s"Node( node: [ layerIndex: $layerIndex, nodeIndex: $nodeIndex, output: $output, delta: $delta], \n)" +
    s"upStream: [${upStream.map(conn => {conn.toString}).mkString}], \n" +
    s"downStreamConn: [${downStream.map(conn => {conn.toString}).mkString}]."
}

object WeightNode {
  def apply(layerIndex: Int, nodeIndex: Int): AbstractNode = new WeightNode(layerIndex, nodeIndex)
}


