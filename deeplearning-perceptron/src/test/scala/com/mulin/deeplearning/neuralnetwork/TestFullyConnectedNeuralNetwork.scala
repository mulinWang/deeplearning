package com.mulin.deeplearning.neuralnetwork

import com.mulin.deeplearning.neuralnetwork.check.GradientCheck
import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer


/**
  * Created by mulin on 2017/11/1.
  */
class TestFullyConnectedNeuralNetwork extends FunSuite {

  test("test network gradient check") {
    val neuralNetwork = FullyConnectedNeuralNetwork(Array(2, 2, 2))

    val testSample = ArrayBuffer(0.9d, 0.1d)
    val testLabel = ArrayBuffer(0.9d, 0.1d)

    val gradientCheck = GradientCheck()

    for(i <- 0 until 5) {
      gradientCheck.gradientCheck(neuralNetwork, testSample, testLabel)
      println("--------------")
    }

  }

}
