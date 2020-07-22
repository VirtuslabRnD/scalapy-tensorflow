package me.shadaj.scalapy.tensorflow.api.scalaUtils

enum DTypeEnum (override private[api] val v: String) extends PythonEnum(v){
  case Float16 extends DTypeEnum("float16")
  case Float32 extends DTypeEnum("float32")
  case Float64 extends DTypeEnum("float64")
  case Bfloat16 extends DTypeEnum("bfloat16")
  case Complex64 extends DTypeEnum("complex64")
  case Complex128 extends DTypeEnum("complex128")
  case Int8 extends DTypeEnum("int8")
  case Uint8 extends DTypeEnum("uint8")
  case Uint16 extends DTypeEnum("uint16")
  case Uint32 extends DTypeEnum("uint32")
  case Uint64 extends DTypeEnum("uint64")
  case Int16 extends DTypeEnum("int16")
  case Int32 extends DTypeEnum("int32")
  case Int64 extends DTypeEnum("int64")
  case Bool extends DTypeEnum("bool")
  case String extends DTypeEnum("string")
  case Qint8 extends DTypeEnum("qint8")
  case Quint8 extends DTypeEnum("quint8")
  case Qint16 extends DTypeEnum("qint16")
  case Quint16 extends DTypeEnum("quint16")
  case Qint32 extends DTypeEnum("qint32")
  case Resource extends DTypeEnum("resource")
  case Variant extends DTypeEnum("variant")
  case Object extends DTypeEnum("object")
}