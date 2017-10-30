package com.mulin.deeplearning.linearUnit

import breeze.plot.{Figure, plot}
import com.mulin.deeplearning.perceptron.Perceptron

class LinearUnit(inputNum: Int, activatorFunc: (Float => Float))
  extends Perceptron (inputNum, activatorFunc){

  override def toString = s"LinearUnit(weight: ${weight.mkString("[", ", ", "]")},  bias: $bias)"

}

object LinearUnit {
  def apply(activatorFunc: (Float => Float), trainingDataSet: (Array[Array[Float]], Array[Int]), inputNum: Int): LinearUnit = {
    val l = new LinearUnit(inputNum, activatorFunc)
    val trainDataSet:(Array[Array[Float]], Array[Int]) = trainingDataSet

    l.train(trainDataSet._1, trainDataSet._2, 10, 0.1f)
    l
  }

  def main(args: Array[String]): Unit = {
    val samples = peopleTrainingDataSet

    val sampleX: Array[Float] = Array(5, 3, 8, 1.4f, 10.1f)
    val sampleY: Array[Float] = Array(5500, 2300, 7600, 1800, 11400)

    val linearUnit = LinearUnit(activatorFunc, samples, 1)

    println(s"$linearUnit")

    val input = Array(3.4f, 15, 1.5f, 6.3f)
    val x = input

    val output = input.map(x => linearUnit.predict(Array(x)))
    val y = output

//    println(s"Work 3.4 years, monthly salary = ${linearUnit.predict(Array(3.4f))}")
//    println(s"Work 15 years, monthly salary = ${linearUnit.predict(Array(15))}")
//    println(s"Work 1.5 years, monthly salary = ${linearUnit.predict(Array(1.5f))}")
//    println(s"Work 6.3 years, monthly salary = ${linearUnit.predict(Array(6.3f))}")

    val f = Figure()
    val p = f.subplot(0)

    p += plot(sampleX, sampleY, '.')
    p += plot(x, y, '+')

    p.xlabel = "x axis"
    p.ylabel = "y axis"
    f.saveas("./lines.png")

  }

  /**
    * 激活函数
    * @param x
    * @return
    */
  def activatorFunc(x: Float): Float = {
    x
  }

  /**
    * 捏造5个人的工作年限 x，与对应的薪资 y
    * @return
    */
  def peopleTrainingDataSet(): (Array[Array[Float]], Array[Int]) = {
    val inputVecs: Array[Array[Float]] = Array(Array(5), Array(3), Array(8), Array(1.4f), Array(10.1f))
    val labels: Array[Int] = Array(5500, 2300, 7600, 1800, 11400)

    (inputVecs, labels)
  }

}