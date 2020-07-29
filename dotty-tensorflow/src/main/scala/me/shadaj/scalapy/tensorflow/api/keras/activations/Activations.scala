package me.shadaj.scalapy.tensorflow.api.keras.activations

import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum

enum Activations(override private[api] val v: String) extends PythonEnum(v){
  case Elu   extends Activations("elu")
  case Relu   extends Activations("relu")
  case Softmax   extends Activations("softmax")
  case Sigmoid   extends Activations("sigmoid")
}
