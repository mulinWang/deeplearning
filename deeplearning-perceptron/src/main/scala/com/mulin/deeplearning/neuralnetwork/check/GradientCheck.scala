package com.mulin.deeplearning.neuralnetwork.check

import com.mulin.deeplearning.neuralnetwork.FullyConnectedNeuralNetwork
import scala.collection.mutable.ArrayBuffer
/**
  * Created by mulin on 2017/11/1.
  */
class GradientCheck {

  def networkError(arr1: ArrayBuffer[Double], arr2: ArrayBuffer[Double]) = {
    arr1.zip(arr2)
      .map(v => ((v._1 - v._2) * (v._1 - v._2)))
      .reduce(_+_)
  }

  def gradientCheck(network: FullyConnectedNeuralNetwork, testSample: ArrayBuffer[Double], testLabel: ArrayBuffer[Double]): Unit = {
    //获取当前样本下每个连接的梯度
    network.getGradient(testSample, testLabel)

    for (conn <- network.connections.connections) {
      val actualGradient = conn.getGradient

      //ϵ
      val epsilon = 10e-4
      conn.weight += epsilon
      val error1 = networkError(network.predict(testSample), testLabel)

      conn.weight -= (epsilon * 2)
      val error2 = networkError(network.predict(testSample), testLabel)

      val exceptedGradient = (error2 - error1) / (2 * epsilon)

      println(s"actualGradient: $actualGradient, exceptedGradient: $exceptedGradient")
    }
  }
}

object GradientCheck {
  def apply(): GradientCheck = new GradientCheck()
}
