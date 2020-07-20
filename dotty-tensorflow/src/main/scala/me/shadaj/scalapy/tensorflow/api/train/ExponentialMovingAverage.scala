package me.shadaj.scalapy.tensorflow.api.train
import me.shadaj.scalapy.tensorflow.train.{ExponentialMovingAverage => PyExponentialMovingAverage}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonType

class ExponentialMovingAverage private[api] (val underlying: PyExponentialMovingAverage) extends PythonType[PyExponentialMovingAverage]
