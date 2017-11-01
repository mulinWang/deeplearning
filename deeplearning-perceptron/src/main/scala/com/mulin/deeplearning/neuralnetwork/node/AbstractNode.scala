package com.mulin.deeplearning.neuralnetwork.node

import com.mulin.deeplearning.neuralnetwork.connection.Connection

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/1.
  */
abstract class AbstractNode(val layerIndex: Int, val nodeIndex: Int) {

  def calculateOutput

  //节点下游的 connection 数组
  val downStream: ArrayBuffer[Connection] = ArrayBuffer.empty
  //节点上游的 connection 数组
  val upStream: ArrayBuffer[Connection] = ArrayBuffer.empty

  //添加 BeanProperty 注解，实现 get/set 方法
  @BeanProperty var output: Double = 1
  @BeanProperty var delta: Double = 0.0d

  /**
    * 链接下层连接层
    * @param conn
    */
  def appendDownStreamConnection(conn: Connection): Unit = {
    downStream += conn
  }

  /**
    * 链接上层连接层
    * @param conn
    */
  def appendUpStreamConnection(conn: Connection): Unit = {
    upStream += conn
  }

  /**
    * 如果该节点属于隐藏层，
    * 则计算delta值，其中
    * δ = y * (1 - y) * ∑δw
    */
  def calculateHiddenLayerDelta(): Unit = {
    val downStreamDelta: Double = downStream
      .map(conn => conn.downStreamNode.delta * conn.weight)
      .reduce(_ + _)
    setDelta(output * (1 - output) * downStreamDelta)
}

  /**
    * 如果该节点属于输出层且为权重节点，
    * 则计算delta值，其中
    * δ = y * (1 - y) * (t - y)
    */
  def calculateOutputLayerDelta(label: Double): Unit = {}
}
