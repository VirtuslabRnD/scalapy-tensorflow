package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.py

object ContextManager {

 def withContext[T <: Context, R](ctx: T)(function: T => R): R = {
   ctx.__enter__()
   val r = function(ctx)
   ctx.__exit__()
   r
 }
}

trait Context{
  def __enter__(): Unit
  // we dont care about those arguments for now
  def __exit__(typ: py.Any = py.None, value: py.Any = py.None, traceback: py.Any = py.None): Unit
}


