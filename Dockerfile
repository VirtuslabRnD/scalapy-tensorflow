FROM debian:buster-slim

RUN set -x \
  `# workaround for error in debian-slim during openjdk installation` \
  && mkdir -p /usr/share/man/man1 \
  && apt-get update \
  && apt-get install --no-install-recommends -y curl git gnupg openjdk-11-jre-headless python3 python3-pip \
  && rm -rf /var/lib/apt/lists/*

RUN set -x \
  && echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list \
  && curl -fsL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add \
  && apt-get update \
  && apt-get install -y sbt \
  && rm -rf /var/lib/apt/lists/*

RUN set -x \
  && git clone https://github.com/VirtuslabRnD/scalapy.git \
  && cd scalapy \
  `# VirtuslabRnD:kwargs as of 27-05-2020` \
  && git checkout 2597f7b23301ddd59067de24d4d3809cf475ae9f \
  && sed -i 's/^scalaVersion in ThisBuild := scala213Version$/scalaVersion in ThisBuild := scala212Version; version in ThisBuild ~= (_.replaceFirst("\\\\+[^+]+$", ""))/' build.sbt \
  && sbt publishLocal

RUN set -x \
 && apt-get update \
 && apt-get install --no-install-recommends -y build-essential python3-dev \
 && rm -rf /var/lib/apt/lists/*
# https://stackoverflow.com/questions/52904639/dockerfile-with-pip-grpcio-is-very-slow-to-build => fix not applied yet
RUN set -x \
  && pip3 install setuptools wheel \
  && pip3 install numpy tensorflow
RUN set -x \
  && apt-get update \
  && apt-get install -y openjdk-11-jdk-headless \
  && rm -rf /var/lib/apt/lists/*
RUN set -x \
  && export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java)))) \
  && pip3 install jep==3.8.2
RUN pip3 install keras

WORKDIR /scalapy-tensorflow
COPY . .
RUN find .
ENV JAVA_OPTS='-Djava.library.path=/usr/local/lib/python3.7/dist-packages/jep'
RUN sbt compile
RUN sbt 'project scalaPyTensorFlowCrossJVM' 'runMain me.shadaj.scalapy.tensorflow.example.Example'
RUN sbt 'project scalaPyTensorFlowCrossJVM' 'runMain me.shadaj.scalapy.tensorflow.example.MnistExample'
