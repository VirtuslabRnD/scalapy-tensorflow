package me.shadaj.scalapy.tensorflow.api.keras.metrics
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum._

enum Metric(override private[api] val v: String) extends PythonEnum(v){
  case AUC   extends Metric("auc")
  case Accuracy   extends Metric("accuracy")
  case BinaryAccuracy   extends Metric("binary_accuracy")
  case BinaryCrossentropy   extends Metric("binary_crossentropy")
}
