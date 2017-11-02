package com.mulin.deeplearning.neuralnetwork

import java.time.LocalDateTime

import com.mulin.deeplearning.neuralnetwork.loader.{ImageLoader, LabelLoader}

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks

/**
  * Created by mulin on 2017/11/2.
  */
class NetworkMain {

  /**
    * 训练模型并评估误差
    * @param network
    */
  def trainAndEvaluate(network: FullyConnectedNeuralNetwork): Unit = {

    var lastErrorRatio = 1.0d
    var epoch = 0
    val loop = new Breaks

    //_.1 dataSet _.2 labels
    val trainData = getTrainDataSet
    val testData = getTestDataSet

    loop.breakable(
      while (true) {
        epoch += 1
        network.train(trainData._1, trainData._2, 0.3d, 1)
        println(s"epoch: $epoch , now: ${LocalDateTime.now}")

        if ((epoch % 10) == 0) {
          val errorRatio = evaluate(network, testData._1, testData._2)
          println(s"epoch: $epoch , errorRatio: $errorRatio")

          if (errorRatio > lastErrorRatio) loop.break
          else lastErrorRatio = errorRatio
        }
      }
    )
  }


  /**
    * 评估错误率
    */
  def evaluate(network: FullyConnectedNeuralNetwork,
               testDataSet: ArrayBuffer[ArrayBuffer[Double]],
               testLabels: ArrayBuffer[ArrayBuffer[Double]]): Double = {

    var error = 0
    val total = testLabels.length

    for (i <- 0 until total) {
      val label = getResult(testLabels(i))
      val predict = getResult(network.predict(testDataSet(i)))

      if(label != predict) error += 1
    }

    (error.toDouble / total.toDouble)
  }

  /**
    * 十维数组根据最大值的下标转输出值
    * 取数组最大值的下标
    * @return
    */
  def getResult(arrayBuffer: ArrayBuffer[Double]): Int =  arrayBuffer.zipWithIndex.maxBy(_._1)._2



  /**
    * 获取训练数据集
    * @return
    */
  def getTrainDataSet(): (ArrayBuffer[ArrayBuffer[Double]], ArrayBuffer[ArrayBuffer[Double]]) = {
    val imageLoader = ImageLoader("/home/hadoop/project/longyuan/train-images.idx3-ubyte", 60000)
    val labelLoader = LabelLoader("/home/hadoop/project/longyuan/train-labels.idx1-ubyte", 60000)
    (imageLoader.load, labelLoader.load)
  }

  /**
    * 获取测试数据集
    * @return
    */
  def getTestDataSet(): (ArrayBuffer[ArrayBuffer[Double]], ArrayBuffer[ArrayBuffer[Double]]) = {
    val imageLoader = ImageLoader("/home/hadoop/project/longyuan/t10k-images.idx3-ubyte", 10000)
    val labelLoader = LabelLoader("/home/hadoop/project/longyuan/t10k-labels.idx1-ubyte", 10000)
    (imageLoader.load, labelLoader.load)
  }

}

object NetworkMain {
  def apply(): NetworkMain = new NetworkMain()

  def main(args: Array[String]): Unit = {
    val network = FullyConnectedNeuralNetwork(Array(784, 300, 10))
    val networkMain = NetworkMain()
    networkMain.trainAndEvaluate(network)

    println(s"network: ${network.toString}")
  }
}

