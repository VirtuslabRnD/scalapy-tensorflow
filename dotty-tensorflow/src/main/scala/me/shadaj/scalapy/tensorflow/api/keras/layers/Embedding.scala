package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{Embedding => PyEmbedding}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._

class Embedding private[api] (override val underlying: PyEmbedding) extends Layer(underlying) {}
