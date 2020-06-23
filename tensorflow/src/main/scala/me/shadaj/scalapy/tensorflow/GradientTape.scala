package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.py

@py.native trait GradientTape extends py.Object {
  // TODO: returns Tensor or None
  def gradient(target: Tensor, sources: Seq[Variable]): Seq[Tensor] = py.native
  def watch(tensor: Tensor): Unit = py.native
  def __enter__(): Unit = py.native
  // TODO: fake context management better
  def __exit__(typ: py.Any = py.None, value: py.Any = py.None, traceback: py.Any = py.None): Unit = py.native
}
