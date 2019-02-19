#!/usr/bin/env bash

SRV_NAME=basics-provider

#查找PID命令
PID_CMD="ps auxf | grep 'jar' | grep "$SRV_NAME" | grep -v bash | awk '{print \$2}'  | head -1"

#获取根目录
BIN_DIR=$(cd `dirname $0`; pwd)
BASE_DIR=`dirname $BIN_DIR`

cd $BASE_DIR
JAR_FILE=`ls -t *$SRV_NAME*.jar | head -1`
if [ ! -f "$BASE_DIR/$JAR_FILE" ];then
    echo "Error: jar文件不存在!"
    exit 1
fi

pid=`eval $PID_CMD`;
if [ "${pid}" != "" ]; then
  if [ -d /proc/$pid ]; then
      echo "服务已启动！";
      exit -1;
  fi
fi

#设置应用环境参数
ENV_OPTS=" -Dspring.profiles.active=dev "

JAVABIN="java"
if [ "${JAVA_HOME}" != "" ]; then
    JAVABIN="$JAVA_HOME/bin/java"
fi

nohup $JAVABIN $JAVA_OPTS $MEM_OPTS $GC_OPTS $ENV_OPTS -jar $BASE_DIR/$JAR_FILE > $SRV_NAME.out 2>&1 &

#检测端口是否正常启动
echo -e "启动成功..."
