package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.{GradientTape => PyGradientTape}
import scalaUtils.{PythonType, CloseableResource}
import scala.language.implicitConversions

class GradientTape private[api] (val underlying: PyGradientTape) extends PythonType[PyGradientTape] with CloseableResource {
  def gradient(target: Tensor, sources: Seq[Variable]): Seq[Tensor] =
    underlying.gradient(target, sources.map(_.underlying)).map(new Tensor(_))
  def watch(tensor: Tensor): Unit = underlying.watch(tensor)

  override private[api] def __enter__(): Unit = underlying.__enter__()
  override private[api] def __exit__(): Unit = underlying.__exit__()

}
