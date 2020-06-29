package me.shadaj.scalapy.tensorflow.scala.utils

import me.shadaj.scalapy.py

object ContextManager {

  def withContext[T <: Context, R](ctx: T)(function: T => R): Option[R] = {
    ctx.__enter__()
    try {
      Some(function(ctx))
    } finally {
      ctx.__exit__()
      None
    }
  }

}

trait Context {
  def __enter__(): Unit
  // we don't care about those arguments for now
  def __exit__(typ: py.Any = py.None, value: py.Any = py.None, traceback: py.Any = py.None): Unit
}
