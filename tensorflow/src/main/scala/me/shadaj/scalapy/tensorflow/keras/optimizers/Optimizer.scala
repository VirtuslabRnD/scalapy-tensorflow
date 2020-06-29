package me.shadaj.scalapy.tensorflow.keras.optimizers

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.compat.v1.Operation
import me.shadaj.scalapy.tensorflow.{Tensor, Variable}

@py.native
trait Optimizer extends py.Object {
  // TODO loss should be a function () => py.Any
  def minimize(loss: py.Any, var_list: Seq[Variable]): Operation = py.native

  def apply_gradients(grads_and_vars: Seq[(Tensor, Variable)]): Operation = py.native
}
