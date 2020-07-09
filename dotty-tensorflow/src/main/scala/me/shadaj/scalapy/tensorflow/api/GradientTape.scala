package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.{GradientTape => PyGradientTape}
import scalaUtils.{PythonType, Context}
import scala.language.implicitConversions

class GradientTape private[api] (val underlying: PyGradientTape) extends PythonType[PyGradientTape] with Context {

  def gradient(target: Tensor, sources: Seq[Variable]): Seq[Tensor] =
    underlying.gradient(target, sources.map(_.underlying)).map(new Tensor(_))
  def watch(tensor: Tensor): Unit = underlying.watch(tensor)

  override private[api] def __enter__(): Unit = underlying.__enter__()
  override private[api] def __exit__(): Unit = underlying.__exit__()

}

object GradientTape {

  implicit val TensorToPyTensor: Conversion[GradientTape, PyGradientTape] = _.underlying

  implicit val PyTensorToTensor: Conversion[PyGradientTape, GradientTape] = new GradientTape(_)
}
