package me.shadaj.scalapy.tensorflow.compat.v1

import me.shadaj.scalapy.py

@py.native
trait Compat extends py.Object {

  def v1: V1 = py.native

}
