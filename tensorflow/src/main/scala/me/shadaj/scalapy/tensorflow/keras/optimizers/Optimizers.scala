package me.shadaj.scalapy.tensorflow.keras.optimizers

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.{PyFunction, PyValue, Reader}
import me.shadaj.scalapy.tensorflow.{Operation, Tensor, Variable}

@py.native trait Optimizers extends py.Object {
  def Adadelta(): Adadelta = py.native

  def SGD(learning_rate: Double, momentum: py.NoneOr[Double]): SGD = py.native
}

@py.native trait Optimizer extends py.Object {
  // TODO loss should be a function () => py.Any
  def minimize(loss: py.Any, var_list: Seq[Variable]): Operation = py.native

  def apply_gradients(grads_and_vars: Seq[(Tensor, Variable)]): Operation = py.native
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
