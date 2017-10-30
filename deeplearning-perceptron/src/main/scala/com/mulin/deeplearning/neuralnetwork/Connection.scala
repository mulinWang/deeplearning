package com.mulin.deeplearning.neuralnetwork

import scala.beans.BeanProperty
import scala.util.Random

/**
  * Created by XingBaoBao on 2017/10/31.
  * 神经网络中的连接对象
  * upStreamNode：连接对象的上游节点
  * downStreamNode：连接对象的下游节点
  */
class Connection(upStreamNode: Node, downStreamNode: Node) {
  //连接权重，初始值为区间 [-1.0, 1.0] 中的随机数
  @BeanProperty var weight: Float = {
    (math.random * (if(Random.nextBoolean()) 1 else -1) ).toFloat
  }

  @BeanProperty var gradient: Float = 0.0f

  /**
    * 计算梯度，
    * 梯度 = 下游节点的增量 * 上游节点的输出
    */
  def calculateGradient(): Float ={
    gradient = downStreamNode.delta * upStreamNode.output
    gradient
  }


  /**
    * 更新权重
    *
    * @param rate
    */
  def updateWeight(rate: Float): Unit = {
    weight += (rate * calculateGradient)

  }
}
