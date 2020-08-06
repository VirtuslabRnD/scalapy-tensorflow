package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.{Tensor => PyTensor}
import scalaUtils.PythonType
import scala.language.implicitConversions
import me.shadaj.scalapy.tensorflow.{float2Tensor, nd2Tensor, seq2Tensor}
import me.shadaj.scalapy.numpy.NDArray

class Tensor private[api] (val underlying: PyTensor) extends PythonType[PyTensor] {}

object Tensor {

  implicit def TensorToPyTensor: Conversion[Tensor, PyTensor] = _.underlying

  implicit def PyTensorToTensor: Conversion[PyTensor, Tensor] = new Tensor(_)
}
