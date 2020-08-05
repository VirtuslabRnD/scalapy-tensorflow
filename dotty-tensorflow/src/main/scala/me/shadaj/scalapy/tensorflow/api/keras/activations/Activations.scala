package me.shadaj.scalapy.tensorflow.api.keras.activations

import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum

enum Activation(override private[api] val v: String) extends PythonEnum(v){
  case Elu   extends Activation("elu")
  case Relu   extends Activation("relu")
  case Softmax   extends Activation("softmax")
  case Sigmoid   extends Activation("sigmoid")
}