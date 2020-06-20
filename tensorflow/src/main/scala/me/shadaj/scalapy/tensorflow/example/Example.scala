package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.tensorflow.Modules._

object Example extends App {
  val tf = tensorflow
  val np = numpy

  val xData = np.random.rand(100).astype(np.float32)
  val yData = (xData * 0.1) + 0.3

  val W = tf.Variable(tf.random.uniform(shape = Seq(1), minval = -1, maxval = 1))
  val b = tf.Variable(tf.zeros(Seq(1)))
  val y = (W * xData) + b

  val loss = tf.reduce_mean(tf.square(y - yData))
  val optimizer = keras.optimizers.SGD(0.5)
  val train = optimizer.minimize(loss)

  val init = tf.global_variables_initializer()

  val sess = tf.Session()
  sess.run(init)

  (0 to 200).foreach { step =>
    sess.run(train)

    if (step % 20 == 0) {
      println(s"$step ${sess.run(W).head} ${sess.run(b).head}")
    }
  }
}
