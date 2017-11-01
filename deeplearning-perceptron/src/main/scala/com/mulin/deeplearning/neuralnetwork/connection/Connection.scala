package com.mulin.deeplearning.neuralnetwork.connection

import com.mulin.deeplearning.neuralnetwork.node.AbstractNode

import scala.beans.BeanProperty
import scala.concurrent.forkjoin.ThreadLocalRandom

/**
  * Created by XingBaoBao on 2017/10/31.
  * 神经网络中的连接对象
  * 记录连接的权重及上下游的节点
  * upStreamNode：连接对象的上游节点
  * downStreamNode：连接对象的下游节点
  */
class Connection(val upStreamNode: AbstractNode, val downStreamNode: AbstractNode) {
  //连接权重，初始值为区间 [-1.0, 1.0] 中的随机数
  @BeanProperty var weight: Double = ThreadLocalRandom.current.nextDouble(-1.0, 1.0).toDouble
  @BeanProperty var gradient: Double = 0.0d

  /**
    * 计算梯度，
    * 梯度 = 下游节点的增量 * 上游节点的输出
      * △ = δ * x
    */
  def calculateGradient(): Double ={
    gradient = downStreamNode.delta * upStreamNode.output
    gradient
  }

  /**
    * 更新权重
    * 先计算一次梯度，在通过梯度的值计算权重
    * weight = η * δ * x
    * @param rate
    */

  def updateWeight(rate: Double): Unit = {
    weight += (rate * calculateGradient)
  }

  override def toString = s"Connection({upStreamNode: [layer index: ${upStreamNode.layerIndex}, node index: ${upStreamNode.nodeIndex}] " +
    s"downStreamNode: [layer index: ${downStreamNode.layerIndex}, node index: ${downStreamNode.nodeIndex}] -> weight: $weight}, gradient: $gradient.)"
}
object Connection {
  def apply(upStreamNode: AbstractNode, downStreamNode: AbstractNode): Connection = new Connection(upStreamNode, downStreamNode)
}