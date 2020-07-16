package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{Bidirectional => PyBidirectional}

class Bidirectional private[api] (override val underlying: PyBidirectional) extends Layer(underlying)
