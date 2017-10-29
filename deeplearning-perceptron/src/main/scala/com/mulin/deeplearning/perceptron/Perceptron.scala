package com.mulin.deeplearning.perceptron


/**
  * and，or，not 真值表感知器实现类
  * 问题：不晓得权重和偏置的增量是如何推导的
  * @param inputNum
  * @param activatorFunc
  */
class Perceptron(inputNum: Int, activatorFunc: (Float => Float)) {

  var weight: Array[Float] = new Array[Float](inputNum)
  var bias = 0.0f

  /**
    * 预测函数
    * @param inputVec
    * @return
    */
  def predict(inputVec: Array[Float]): Float = {
    val y = activatorFunc(inputVec.zip(weight)
      .map(t => t._1 * t._2)
      .sum + bias)
    y
  }

  /**
    * 训练函数
    * @param inputVecs
    * @param labels
    * @param iteration
    * @param rate
    */
  def train(inputVecs: Array[Array[Float]], labels: Array[Int], iteration: Int, rate: Float): Unit = {
     for (i <- 0 until iteration) {
       oneIteration(inputVecs, labels, rate)
     }
  }

  /**
    * 单次学习迭代
    * @param inputVecs
    * @param labels
    * @param rate
    */
  def oneIteration(inputVecs: Array[Array[Float]], labels: Array[Int], rate: Float): Unit = {
    inputVecs.zip(labels)
      .map(t => {
        val output = predict(t._1)
        updateWeightsAndBias(t._1, output, t._2, rate)
      })
  }

  /**
    * 更新权重和偏置函数
    * @param inputVec
    * @param output
    * @param label
    * @param rate
    */
  def updateWeightsAndBias(inputVec: Array[Float], output: Float, label: Int, rate: Float): Unit = {
    val delta = label - output
    weight = inputVec.zip(weight)
      .map(t => t._2 + (rate * delta * t._1))

    bias += (rate * delta)
  }

  override def toString = s"Perceptron(weight: ${weight.mkString("[", ", ", "]")},  bias: $bias)"

}


object Perceptron {

  /**
    * 使用感知器训练，并返回训练过后的感知器
    * @return
    */
  def apply(activatorFunc: (Float => Float), trainingDataSet: (Array[Array[Float]], Array[Int]), inputNum: Int): Perceptron = {
    val p = new Perceptron(inputNum, activatorFunc)
    val trainDataSet:(Array[Array[Float]], Array[Int]) = trainingDataSet

    p.train(trainDataSet._1, trainDataSet._2, 10, 0.1f)
    p
  }

  def main(args: Array[String]): Unit = {
    val andPerceptron = Perceptron(activatorFunc, andTrainingDataSet, 2)

    println(s"and: ${andPerceptron}")

    println(s"0 and 0 = ${andPerceptron.predict(Array(0, 0))}")
    println(s"0 and 1 = ${andPerceptron.predict(Array(0, 1))}")
    println(s"1 and 0 = ${andPerceptron.predict(Array(1, 0))}")
    println(s"1 and 1 = ${andPerceptron.predict(Array(1, 1))}")


    val orPerceptron = Perceptron(activatorFunc, orTrainingDataSet, 2)

    println(s"or: ${orPerceptron}")

    println(s"0 or 0 = ${orPerceptron.predict(Array(0, 0))}")
    println(s"0 or 1 = ${orPerceptron.predict(Array(0, 1))}")
    println(s"1 or 0 = ${orPerceptron.predict(Array(1, 0))}")
    println(s"1 or 1 = ${orPerceptron.predict(Array(1, 1))}")

    val notPerceptron = Perceptron(activatorFunc, notTrainingDataSet, 1)

    println(s"or: ${notPerceptron}")

    println(s"not 0 = ${notPerceptron.predict(Array(0))}")
    println(s"not 1 = ${notPerceptron.predict(Array(1))}")
  }

  /**
    * 真值表激活函数
    * @param x
    * @return
    */
  def activatorFunc(x: Float): Int = {
    if (x > 0.0) 1 else 0
  }

  /**
    * 构造and真值表训练集
    * @return
    */
  def andTrainingDataSet(): (Array[Array[Float]], Array[Int]) = {
    val inputVecs: Array[Array[Float]] = Array(Array(1, 1), Array(0, 0), Array(1, 0), Array(0, 1))
    val labels: Array[Int] = Array(1, 0, 0, 0)

    (inputVecs, labels)
  }

  /**
    * 构造or真值表训练集
    * @return
    */
  def orTrainingDataSet(): (Array[Array[Float]], Array[Int]) = {
    val inputVecs: Array[Array[Float]] = Array(Array(1, 1), Array(0, 0), Array(1, 0), Array(0, 1))
    val labels: Array[Int] = Array(1, 0, 1, 1)

    (inputVecs, labels)
  }

  /**
    * 构造not真值表训练集
    * @return
    */
  def notTrainingDataSet(): (Array[Array[Float]], Array[Int]) = {
    val inputVecs: Array[Array[Float]] = Array(Array(1), Array(0))
    val labels: Array[Int] = Array(0, 1)

    (inputVecs, labels)
  }

}
