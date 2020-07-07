package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.{Tensor => PyTensor}
import scalaUtils.PythonType
import scala.language.implicitConversions
import me.shadaj.scalapy.tensorflow.{ double2Tensor, nd2Tensor}
import me.shadaj.scalapy.numpy.NDArray

class Tensor private[api] (val underlying: PyTensor) extends PythonType[PyTensor] {

}

object Tensor {

  implicit val TensorToPyTensor: Conversion[Tensor, PyTensor] = _.underlying

  implicit val PyTensorToTensor: Conversion[PyTensor, Tensor] = new Tensor(_)
  
  implicit val double2Tensor: Conversion[Double, Tensor] = new Tensor(_)

  implicit val nd2Tensor: Conversion[NDArray[Double], Tensor] = new Tensor(_)
}