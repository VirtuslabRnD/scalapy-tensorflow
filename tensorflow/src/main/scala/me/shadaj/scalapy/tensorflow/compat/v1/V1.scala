package me.shadaj.scalapy.tensorflow.compat.v1

import me.shadaj.scalapy.numpy.PythonSeq
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.Tensor
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait V1 extends py.Object with PythonModule {
  def placeholder(`type`: String): Tensor = py.native

  def placeholder(`type`: String, shape: PythonSeq[py.NoneOr[Int]]): Tensor = py.native

  def global_variables_initializer(): Operation = py.native

  def Session(): Session = py.native

  def InteractiveSession(): Session = py.native

}
