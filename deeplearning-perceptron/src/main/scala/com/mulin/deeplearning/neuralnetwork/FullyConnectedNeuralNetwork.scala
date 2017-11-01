package com.mulin.deeplearning.neuralnetwork

import com.mulin.deeplearning.neuralnetwork.connection.{Connection, Connections}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/1.
  * 全连接神经网络
  * layerNodeCountArr：层节点数数组
  * 如：
  * 3层每次10节点，则 layerNodeCountArr 为
  * [10, 10, 10]
  */
class FullyConnectedNeuralNetwork(val layerNodeCountArr: Array[Int]) {

  //连接对象集合
  val connections = Connections()

  //节点层对象集合
  val layers = ArrayBuffer.empty[Layer]
  val layerCount = layerNodeCountArr.length
  val nodeCount = 0

  //初始化Layer层
  for (i <- 0 until layerCount) {
    layers += Layer(i, layerNodeCountArr(i))
  }

  /**
    * 遍历 layer 集合，
    * 生成单层 layer 节点的 conn 对象，
    * 通过 conn 对象连接上下游 node
    * 初始化 connections 集合
    */
  for (i <- 0 until layerCount - 1) {

    for (m <- layers(i).nodes; n <- layers(i + 1).nodes; if (!n.equals(layers(i + 1).nodes.last))) {
      val conn = Connection(m, n)
      conn.downStreamNode.appendUpStreamConnection(conn)
      conn.upStreamNode.appendDownStreamConnection(conn)

      connections.appendConnection(conn)
    }
  }

  /**
    * 计算每个节点 node 的 delta 值
    */
  def calculateDelta(labels: ArrayBuffer[Double]): Unit = {
    //layers 的最后一层 为输出层
    val outputNodes = layers.last.nodes

    //计算输出层 delta
    for (i <- 0 until labels.length) {
      outputNodes(i).calculateOutputLayerDelta(labels(i))
    }

    //去除输出层，反向传播计算隐藏层 delta
    for(layer <- layers.slice(0, layers.length -1).reverse) {
      for (node <- layer.nodes) {
        node.calculateHiddenLayerDelta()
      }
    }
  }

  /**
    * 更新每个 conn 连接的权重
    */
  def updateWeight(rate: Double): Unit = {
    for (layer <- layers if (!layer.equals(layers.last))) {
      for (node <- layer.nodes) {
        for (conn <- node.downStream) {
          conn.updateWeight(rate)
        }
      }
    }
  }

  /**
    * 计算每个 conn 连接的梯度
    */
  def calculateGradient(): Unit = {
    for (layer <- layers.slice(0, layers.length - 1)) {
      for (node <- layer.nodes) {
        for (conn <- node.downStream) {
          conn.calculateGradient
        }
      }
    }
  }

    /**
      * 根据输入样本预测输出值，
      * sample 为特征向量，输入层的每个输入值
      * @param sample
      */
    def predict(sample: ArrayBuffer[Double]): ArrayBuffer[Double] = {
      layers(0).setInputLayerOutPut(sample)

      for (i <- 1 until layers.length) {
        layers(i).calculateNodeOutput
      }

      //返回输出层的所有权重节点
      layers.last.nodes
        .slice(0, layers.last.nodes.length - 1)
        .map(node => node.output)
    }

    /**
      * 训练单个样本
      * @param sample
      * @param labels
      * @param rate
      */
    def trainOneSample(sample: ArrayBuffer[Double], labels: ArrayBuffer[Double], rate: Double): Unit = {
      predict(sample)
      calculateDelta(labels)
      updateWeight(rate)
    }

    /**
      *
      * @param dataSet
      * @param labels
      * @param rate
      * @param iteration
      */
    def train(dataSet: ArrayBuffer[ArrayBuffer[Double]], labels: ArrayBuffer[ArrayBuffer[Double]], rate: Double, iteration: Int): Unit = {
      for (i <- 0 until iteration; d <- 0 until dataSet.length) {
        trainOneSample(dataSet(d), labels(d), rate)
      }
    }

    /**
      * 获取网络在一个样本下的梯度
      * @param sample
      * @param labels
      */
    def getGradient(sample: ArrayBuffer[Double], labels: ArrayBuffer[Double]): Unit = {
      predict(sample)
      calculateDelta(labels)
      calculateGradient()
    }
}

object FullyConnectedNeuralNetwork {
  def apply(layerNodeCountArr: Array[Int]): FullyConnectedNeuralNetwork = new FullyConnectedNeuralNetwork(layerNodeCountArr)
}
