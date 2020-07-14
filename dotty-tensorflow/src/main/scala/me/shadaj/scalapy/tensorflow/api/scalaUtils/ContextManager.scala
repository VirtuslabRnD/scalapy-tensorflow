package me.shadaj.scalapy.tensorflow.api.scalaUtils

object CloseableResourceManager {

  def withResource[T <: CloseableResource, R](ctx: T)(function: T => R): Option[R] = {
    ctx.__enter__()
    try {
      Some(function(ctx))
    } finally {
      ctx.__exit__()
      None
    }
  }

}

trait CloseableResource {
  // At this level of abstraction I don't see a use for arguments of this functions
  private[api] def __enter__(): Unit
  private[api] def __exit__(): Unit
}
