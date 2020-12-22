package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.numpy.PythonSeq
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.Context

@py.native
trait GradientTape extends py.Object with Context {
  // TODO: returns Tensor or None
  def gradient(target: Tensor, sources: PythonSeq[Variable]): Seq[Tensor] = py.native
  def watch(tensor: Tensor): Unit = py.native
  def __enter__(): Unit = py.native
  def __exit__(typ: py.Any = py.None, value: py.Any = py.None, traceback: py.Any = py.None): Unit = py.native
}
