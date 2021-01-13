package me.shadaj.scalapy.tensorflow.api.keras.models
import me.shadaj.scalapy.tensorflow.keras.models.{Sequential => PySequential}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonType
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.scala.utils.Modules.{numpy => np}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum._
import me.shadaj.scalapy.tensorflow.api.keras.layers._
import me.shadaj.scalapy.tensorflow.api.keras.optimizers._
import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.py.PyFunction

import scala.language.implicitConversions
import me.shadaj.scalapy.numpy.PythonSeq
import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.SeqConverters
import me.shadaj.scalapy.tensorflow.TensorFlow
import me.shadaj.scalapy.tensorflow.nd2Tensor
import me.shadaj.scalapy.py.SeqConverters
import me.shadaj.scalapy.tensorflow.scala.utils.Modules._
import me.shadaj.scalapy.numpy.{NDArray, PythonSeq}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption.toScalaOption
import me.shadaj.scalapy.readwrite.Writer

class Sequential private[api] (val underlying: PySequential) extends PythonType[PySequential] {

  def add(layer: Layer): Unit = underlying.add(layer.underlying)

  def compile(
      optimizer: OptimizerEnum | Optimizer = OptimizerEnum.RMSprop,
      loss: Option[PyFunction] = None,
      metrics: PythonSeq[String] = PythonSeq.emptyString,
      lossWeights: py.NoneOr[PythonSeq[(Double, Double)]] = py.None,
      sampleWeightMode: py.NoneOr[String] = None,
      weightedMetrics: PythonSeq[String] = PythonSeq.emptyString,
      targetTensors: py.NoneOr[String] = None
  ) =
    underlying.compile(
      optimizer,
      loss,
      metrics,
      lossWeights,
      sampleWeightMode,
      weightedMetrics,
      targetTensors
    )

  // TODO issue #39: Add generic number types
  def fit(
      x: NDArray[Float],
      y: NDArray[Float],
      batchSize: Option[Int] = None,
      epochs: Int = 1,
      verbose: Int = 1,
      validationData: Option[(NDArray[Float], NDArray[Float])] = None
  ): Unit = {
    underlying.fit(
      x,
      y,
      batchSize,
      epochs,
      verbose,
      validationData
    )
  }

  def evaluate(
      x: NDArray[Float],
      y: NDArray[Float],
      batchSize: Option[Int] = None,
      verbose: Int = 1
  ): Seq[Double] =
    underlying.evaluate(x, y, batchSize, verbose)

}
