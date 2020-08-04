package me.shadaj.scalapy.tensorflow.api.train

import me.shadaj.scalapy.tensorflow.train.{Train => PyTrain}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule

class Train private[api] (val underlying: PyTrain) extends PythonModule[PyTrain] {

  def ExponentialMovingAverage: ExponentialMovingAverage = new ExponentialMovingAverage(underlying.ExponentialMovingAverage)

}
