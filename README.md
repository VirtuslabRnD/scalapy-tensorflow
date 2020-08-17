# scalapy-tensorflow

[![](https://travis-ci.org/VirtuslabRnD/scalapy-tensorflow.svg?branch=develop)](https://travis-ci.com/VirtuslabRnD/scalapy-tensorflow)
![](https://img.shields.io/maven-central/v/me.shadaj/scalapy-tensorflow_2.12.svg)

Static facades for using [TensorFlow](https://www.tensorflow.org/) in [ScalaPy](https://scalapy.dev/).
Currently contains minimal bindings for usage with TensorFlow.

## Installation
`scalapy-tensorflow` is published on Maven Central, so you can add it to your project by including it as a dependency.

```scala
libraryDependencies += "me.shadaj" %% "scalapy-tensorflow" % "0.1.0"
```

## Development
Libraries required to run scala-native are listed here: https://scala-native.readthedocs.io/en/v0.3.9-docs/user/setup.html.
Likely just `libunwind` and `re2` should be enough on a typical Linux distro.

To quickly start using this library a development container image with all necessary configuration has been provided `Dockerfile.bench`.

Use container either via VS Code dev containers or using docker client by running this commands:
```
docker build -f Dockerfile.bench . -t tensorflow
docker run  -v `pwd`:/scalapy-tensorflow/ -w /scalapy-tensorflow/ -it tensorflow /bin/bash
```

## Performance
Running performance testing in the aforementioned container comparing `BidirectionalLSTMExample.scala` with the python version `BidirectionalLSTMExample.py` showed there is no noticeable difference in learning speed. It's expected as the learning happens on the python side either way.

Results:

|            | Scala         | Python        |
|------------|---------------|---------------|
| Total time | 108.5         | 107.8         |
| Epoch 1    | 46s 59ms/step | 41s 52ms/step |
| Epoch 2    | 41s 53ms/step | 48s 61ms/step |
| Loss       | 0.64          | 0.64          |
| Accuracy   | 0.61          | 0.61          |

