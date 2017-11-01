package com.mulin.deeplearning.neuralnetwork.node



/**
  * Created by mulin on 2017/11/1.
  * 偏置项节点，输出值为常量1
  */
class BiasNode(layerIndex: Int, nodeIndex: Int) extends AbstractNode(layerIndex, nodeIndex){

  override def toString = s"ConstantNode( node: [ layerIndex: $layerIndex, nodeIndex: $nodeIndex ], \n)" +
  s"downStreamConn: [${downStream.map(conn => {conn.toString}).mkString}]."

  override def calculateOutput(): Unit = {}
}

object BiasNode {
  def apply(layerIndex: Int, nodeIndex: Int): AbstractNode = new BiasNode(layerIndex, nodeIndex)
}

