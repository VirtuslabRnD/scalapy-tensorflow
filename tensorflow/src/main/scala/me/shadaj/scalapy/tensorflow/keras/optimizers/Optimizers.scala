package me.shadaj.scalapy.tensorflow.keras.optimizers

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.{ PyValue, Reader }
import me.shadaj.scalapy.tensorflow.{ Operation, Tensor }

@py.native trait Optimizers extends py.Object {
  def Adadelta(): Adadelta = py.native

  def SGD(learningRate: Double): SGD = py.native
}

@py.native trait Optimizer extends py.Object {
  def minimize(loss: Tensor): Operation = py.native
}

@py.native class Adadelta(val value: PyValue) extends Optimizer

object Adadelta {
  implicit val reader: Reader[Adadelta] = new Reader[Adadelta] {
    override def read(v: PyValue): Adadelta = new Adadelta(v)
  }
}

@py.native class SGD(val value: PyValue) extends Optimizer

object SGD {
  implicit val reader: Reader[SGD] = new Reader[SGD] {
    override def read(v: PyValue): SGD = new SGD(v)
  }
}
