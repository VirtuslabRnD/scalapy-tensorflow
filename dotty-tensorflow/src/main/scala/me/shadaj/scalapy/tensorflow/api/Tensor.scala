package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.{Tensor => PyTensor}
import scalaUtils.PythonType
import scala.language.implicitConversions
import me.shadaj.scalapy.tensorflow.{float2Tensor, nd2Tensor, seq2Tensor}
import me.shadaj.scalapy.numpy.NDArray

class Tensor private[api] (val underlying: PyTensor) extends PythonType[PyTensor] {}

object Tensor {

  implicit val TensorToPyTensor: Conversion[Tensor, PyTensor] = _.underlying

  implicit val PyTensorToTensor: Conversion[PyTensor, Tensor] = new Tensor(_)

  implicit val float2Tensor: Conversion[Float, Tensor] = new Tensor(_)

  implicit val nd2Tensor: Conversion[NDArray[Float], Tensor] = new Tensor(_)

  implicit val seq2Tensor: Conversion[Seq[Int], Tensor] = new Tensor(_)
}
