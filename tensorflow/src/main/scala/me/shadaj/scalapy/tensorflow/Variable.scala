package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.py

@py.native
trait Variable extends Tensor {
  def read_value(): Tensor = py.native
  def assign(value: Tensor): Unit = py.native
}
