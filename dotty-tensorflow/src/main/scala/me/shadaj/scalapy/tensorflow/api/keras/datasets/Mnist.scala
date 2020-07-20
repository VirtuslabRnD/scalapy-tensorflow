package me.shadaj.scalapy.tensorflow.api.keras.datasets

import me.shadaj.scalapy.tensorflow.keras.datasets.{Mnist => PyMnist}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.numpy.NDArray

class Mnist private[api] (val underlying: PyMnist) extends PythonType[PyMnist] {
  def loadData(path: String = "mnist.npz"): ((NDArray[Long], NDArray[Long]), (NDArray[Long], NDArray[Long])) =
    underlying.load_data(path)
}
