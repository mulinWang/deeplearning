package com.mulin.deeplearning.neuralnetwork.connection

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mulin on 2017/11/1.
  * 记录连接对象集合
  */
class Connections {
  // connection 数组
  val connections: ArrayBuffer[Connection] = ArrayBuffer.empty


  def appendConnection(conn: Connection): Unit = {
    connections += conn
  }

  override def toString = s"Connections(${connections.mkString})"
}

object Connections {
  def apply(): Connections = new Connections()
}
