package com.mulin.deeplearning.neuralnetwork

import breeze.numerics.exp
import shapeless.T

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

/**
  * Created by XingBaoBao on 2017/10/30.
  * 神经网络中的节点对象
  */

 class Node(layerIndex: Int, nodeIndex: Int) {

  //节点下游的 connection 数组
  val downStream = ArrayBuffer.empty
  //节点上游的 connection 数组
  val upStream = ArrayBuffer.empty

  //添加 BeanProperty 注解，实现 get/set 方法
  @BeanProperty var output: Float = 0.0f
  @BeanProperty var delta: Float = 0.0f

  /**
    * 链接下层连接层
    * @param conn
    */
  def appendDownStreamConnection(conn: ArrayBuffer[T]): Unit = {
    downStream ++ conn
  }


  /**
    * 链接上层连接层
    * @param conn
    */
  def appendUpStreamConnection(conn: ArrayBuffer[T]): Unit = {
    upStream ++ conn
  }



  /**
    * sigmoid 函数（激活函数）
    * @param x
    * @return
    */
  def sigmoid(x: Float): Float = (1.0 / (exp(x * -1.0) + 1.0)).toFloat

  /**
    * 计算节点输出
    */
  def calculateOutput(): Unit ={

  }
}

object Node {
  def apply(layerIndex: Int, nodeIndex: Int): Node = new Node(layerIndex, nodeIndex)

  def main(args: Array[String]): Unit = {
    val node = Node(0, 0)
  }
}


