FROM registry.cn-beijing.aliyuncs.com/aicyber-docker/deploy-base:jdk-8-alpine
ENV JAVA_ARGS="-Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
COPY ./target/nettyConsumer.jar ./
#EXPOSE 8083
CMD java -jar  $JAVA_ARGS $JAVA_OPTS /nettyConsumer.jar