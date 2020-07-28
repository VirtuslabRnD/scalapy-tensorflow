package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{LSTM => PyLSTM}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._

/**
  * Long short-term memory (LSTM) is an artificial recurrent neural network (RNN) architecture.
  */
class LSTM private[api] (override val underlying: PyLSTM) extends Layer(underlying)
