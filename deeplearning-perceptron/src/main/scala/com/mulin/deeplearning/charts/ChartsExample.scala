package com.mulin.deeplearning.charts

import breeze.linalg._
import breeze.plot._


class ChartsExample  {
  def show: Unit = {
    val a = new DenseVector[Int](1 to 3 toArray)
    val b = new DenseMatrix[Int](3, 3, 1 to 9 toArray)

    val f = Figure()
    val p = f.subplot(0)
    val x: DenseVector[Double] = linspace(0.0, 1.0)//曲线可画区间
//    p += plot(x, x :^ 2.0)
//    p += plot(x, x :^ 3.0, '.')
    p += plot(x, x :* 2.0)

    p.xlabel = "x axis"
    p.ylabel = "y axis"
    f.saveas("./lines.png")
  }

}

object ChartsExample {
  def main(args: Array[String]): Unit = {
    val chartsExample = new ChartsExample
    chartsExample.show
  }
}